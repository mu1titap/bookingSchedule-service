package com.multitab.sessionRequest.application.service;


import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import com.multitab.sessionRequest.application.mapper.SessionDtoMapper;
import com.multitab.sessionRequest.application.port.in.CancelSessionUserUseCase;
import com.multitab.sessionRequest.application.port.in.MentoringServiceCallUseCase;
import com.multitab.sessionRequest.application.port.in.SendMessageUseCase;
import com.multitab.sessionRequest.application.port.in.SessionUserInquiryUseCase;
import com.multitab.sessionRequest.application.port.in.dto.CancelSessionDto;
import com.multitab.sessionRequest.application.port.out.SessionUserRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.dto.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.CancelSessionUserMessage;
import com.multitab.sessionRequest.domain.Status;
import com.multitab.sessionRequest.domain.model.SessionRequestDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class CancelSessionUserService implements CancelSessionUserUseCase {
    private final SessionUserRepositoryOutPort sessionUserRepositoryOutPort;
    private final SessionUserInquiryUseCase sessionUserInquiryUseCase;
    private final SendMessageUseCase sendMessageUseCase;
    private final MentoringServiceCallUseCase mentoringServiceCallUseCase;
    @Override
    public void cancelSessionUser(CancelSessionDto cancelSessionDto) {
        // 세션 취소기한 확인 (예약마감일 지나면 불가)
        SessionRequestDomain.isDeadlineValid(cancelSessionDto.getDeadlineDate());
        // 멘티가 세션참가등록 이력 검사
        SessionUserResponseOutDto sessionUserOut = sessionUserInquiryUseCase
                .getSessionUserOutDtoBySessionUuidAndMenteeUuid(cancelSessionDto.getSessionUuid(), cancelSessionDto.getMenteeUuid());
        // 세션요청도메인 [사용자 취소 상태]
        SessionRequestDomain sessionRequestDomain = SessionRequestDomain.createCancelSessionUser(sessionUserOut);
        // 세션 취소상태 업데이트
        Integer count = sessionUserRepositoryOutPort.cancelSessionUser(SessionDtoMapper.from(sessionRequestDomain));
        boolean shouldOpenSession = false;
        if(count > 0){
            SessionResponseOutDto sessionResponse = mentoringServiceCallUseCase.getSessionOutDtoByUuid(cancelSessionDto.getSessionUuid());
            if(sessionResponse.getIsClosed()) { // 취소 수행 전 세션이 닫힌 상태라면 취소했으니 열어 줌
                mentoringServiceCallUseCase.openSession(cancelSessionDto.getSessionUuid());
                shouldOpenSession = true;
            }
            // "세션 참가 취소 이벤트 메시지 전송"
            sendMessageUseCase.sendCancelRegisterSessionUserMessage("cancel-session-user",
                    getCancelSessionUserMessageDto(cancelSessionDto, sessionResponse, shouldOpenSession));
            log.info("cancelSessionUser : 세션 참가 취소 완료");
        }
    }

    private CancelSessionUserMessage getCancelSessionUserMessageDto(CancelSessionDto cancelSessionDto,
                                                                    SessionResponseOutDto sessionResponse,
                                                                    boolean shouldOpenSession) {
        return CancelSessionUserMessage.builder()
                .menteeUuid(cancelSessionDto.getMenteeUuid())
                .sessionUuid(cancelSessionDto.getSessionUuid())
                .startDate(sessionResponse.getStartDate())
                .shouldOpenSession(shouldOpenSession)
                .build();
    }


}
