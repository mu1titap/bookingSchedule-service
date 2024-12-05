package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.adaptor.out.feignClient.PaymentServiceFeignClient;
import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import com.multitab.sessionRequest.adaptor.out.feignClient.vo.SessionPaymentVo;
import com.multitab.sessionRequest.adaptor.out.mongo.dto.MentoringSessionResponseDto;
import com.multitab.sessionRequest.adaptor.out.mongo.persistence.MentoringSessionMongoAdapter;
import com.multitab.sessionRequest.application.port.in.MentoringServiceCallUseCase;
import com.multitab.sessionRequest.application.port.in.RegisterSessionUserUseCase;
import com.multitab.sessionRequest.application.port.in.SendMessageUseCase;
import com.multitab.sessionRequest.application.port.in.SessionUserInquiryUseCase;
import com.multitab.sessionRequest.application.port.in.dto.RegisterSessionDto;
import com.multitab.sessionRequest.application.port.out.MentoringServiceCallOutPort;
import com.multitab.sessionRequest.application.port.out.SendMessageOutPort;
import com.multitab.sessionRequest.application.port.out.SessionUserRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.ReRegisterSessionOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.RegisterSessionOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;
import com.multitab.sessionRequest.common.entity.BaseResponse;
import com.multitab.sessionRequest.domain.Status;
import com.multitab.sessionRequest.domain.model.SessionRequestDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class RegisterSessionUserService implements RegisterSessionUserUseCase {
    private final MentoringServiceCallUseCase mentoringServiceCallUseCase;
    private final SessionUserInquiryUseCase sessionUserInquiryUseCase;
    private final SessionUserRepositoryOutPort sessionUserRepositoryOutPort;
    private final SendMessageOutPort sendMessageOutPort;
    private final PaymentServiceFeignClient paymentServiceFeignClient;
    private final MentoringSessionMongoAdapter mentoringSessionMongoAdapter;

    //@Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void registerSessionUser(RegisterSessionDto dto) {
        String uuid = dto.getSessionUuid();
        // 세션 상태 확인
        SessionResponseOutDto sessionResponseOut = mentoringServiceCallUseCase.getSessionOutDtoByUuid(uuid);
        // 세션 상태 검사 , 예약 마감일 검사
        SessionRequestDomain.isValidSessionState(sessionResponseOut.getIsClosed());
        SessionRequestDomain.isDeadlineValid(sessionResponseOut.getDeadlineDate());
        // 대기상태의 참가자 리스트 조회
        List<SessionUserResponseOutDto> sessionUserListOut =
                sessionUserInquiryUseCase.getSessionUserOutDtoBySessionUuid(uuid, Status.PENDING);
        // 최대 신청인원수 + 멘티중복신청 검사
        SessionRequestDomain.validateMenteeAndMaxHeadCount(sessionUserListOut, dto.getMenteeUuid(), sessionResponseOut.getMaxHeadCount());
        // 세션 참가신청 상태 확인 (이미 참가상태면 에러,취소 상태면 다시 대기상태로 업데이트)
        SessionUserResponseOutDto sessionUserResponse =
                sessionUserInquiryUseCase.getSessionUserOutDtoBySessionUuidAndMenteeUuid(uuid, dto.getMenteeUuid());
        // 최초 세션 참가 신청 (insert)
        if( sessionUserResponse == null ) {
            // dto 생성
            MentoringSessionResponseDto mentoringSessionResponseDto =
            mentoringSessionMongoAdapter.getMentorUuidBySessionUuid(dto.getSessionUuid());
            log.info("before FeignClient");
            log.info("mentoringSessionResponseDto: {}", mentoringSessionResponseDto);
            // 결제 요청

            SessionPaymentVo vo = SessionPaymentVo.builder()
                .sessionUuid(dto.getSessionUuid())
                .menteeUuid(dto.getMenteeUuid())
                .mentorUuid(mentoringSessionResponseDto.getMentorUuid())
                .volt(Integer.parseInt(mentoringSessionResponseDto.getPrice())).build();
            log.info("vo " + vo.toString());
            BaseResponse<Void> response =
            paymentServiceFeignClient.paymentSession(vo);
            log.info("response: {}", response);
            log.info("after FeignClient");

            SessionRequestDomain domain =
                    SessionRequestDomain.createSessionRequestDomain(dto.getSessionUuid(), dto.getMenteeUuid());
            AfterSessionUserOutDto afterSessionUserOutDto =
                    sessionUserRepositoryOutPort.registerSessionUser(RegisterSessionOutDto.from(domain));
            // 세션 참가 후 최대정원 다 찼는지 확인
            Boolean closedSession = domain.isClosedSession(sessionUserListOut.size(), sessionResponseOut.getMaxHeadCount());

            // 정원 다 찼으면 세션 command table update
            if(closedSession) mentoringServiceCallUseCase.closeSession(uuid);
            // "세션 참가등록" 메시지 발행
            afterSessionUserOutDto.setMentoringName(dto.getMentoringName());
            afterSessionUserOutDto.setIsClosed(closedSession);
            afterSessionUserOutDto.setMenteeImageUrl(dto.getUserImageUrl());
            sendMessageOutPort.sendRegisterSessionUserMessage("register-session-user", afterSessionUserOutDto);
        }
        // 취소 -> 대기상태로 업데이트 (취소했다가 다시 신청한 경우임)
        else if( sessionUserResponse.getStatus() == Status.CANCELLED_BY_USER ) {
            // 재등록
            SessionRequestDomain domain =
                    SessionRequestDomain.reCreateSessionRequestDomain(dto.getSessionUuid(), dto.getMenteeUuid(), sessionUserResponse.getId());
            Integer count = sessionUserRepositoryOutPort.reRegisterSessionUser(ReRegisterSessionOutDto.from(domain));
            if( count > 0 ){
                Boolean closedSession = domain.isClosedSession(sessionUserListOut.size(), sessionResponseOut.getMaxHeadCount());
                boolean shouldCloseSession = false;
                if(closedSession) {
                    mentoringServiceCallUseCase.closeSession(uuid);
                    shouldCloseSession = true;
                }
                // "세션 참가 재등록" 메시지 발행
                sendMessageOutPort.sendReRegisterSessionUserMessage("re-register-session-user",
                        getReRegisterSessionUserMessage(dto, sessionResponseOut, shouldCloseSession));
            }
        }
    }

    private  ReRegisterSessionUserMessage getReRegisterSessionUserMessage(RegisterSessionDto dto, SessionResponseOutDto sessionResponseOut, boolean shouldCloseSession) {
        return ReRegisterSessionUserMessage.builder()
                .sessionUuid(dto.getSessionUuid())
                .menteeUuid(dto.getMenteeUuid())
                .startDate(sessionResponseOut.getStartDate())
                .shouldCloseSession(shouldCloseSession)
                .build();
    }


}
