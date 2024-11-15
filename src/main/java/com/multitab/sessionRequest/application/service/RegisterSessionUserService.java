package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import com.multitab.sessionRequest.application.port.in.MentoringServiceCallUseCase;
import com.multitab.sessionRequest.application.port.in.RegisterSessionUserUseCase;
import com.multitab.sessionRequest.application.port.in.SendMessageUseCase;
import com.multitab.sessionRequest.application.port.in.SessionUserInquiryUseCase;
import com.multitab.sessionRequest.application.port.in.dto.RegisterSessionDto;
import com.multitab.sessionRequest.application.port.out.SessionUserRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.dto.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.RegisterSessionOutDto;
import com.multitab.sessionRequest.domain.model.SessionRequestDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class RegisterSessionUserService implements RegisterSessionUserUseCase {
    private final MentoringServiceCallUseCase mentoringServiceCallUseCase;
    private final SessionUserInquiryUseCase SessionUserInquiryUseCase;
    private final SessionUserRepositoryOutPort sessionUserRepositoryOutPort;
    private final SendMessageUseCase sendMessageUseCase;

    //@Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void registerSessionUser(RegisterSessionDto dto) {
        String uuid = dto.getSessionUuid();
        // 세션 상태 확인
        SessionResponseOutDto sessionResponseOut = mentoringServiceCallUseCase.getSessionOutDtoByUuid(uuid);
        SessionRequestDomain domain =
                SessionRequestDomain.createSessionRequestDomain(dto.getSessionUuid(), dto.getMenteeUuid());
        // 세션 상태 검사
        domain.isValidSessionState(sessionResponseOut.getIsClosed());
        // 예약 마감일 검사
        domain.isDeadlineValid(sessionResponseOut.getDeadlineDate());
        // 참가자 리스트 조회
        // todo : 여기서 참가자리스트 테이블 조회 (9명)
        List<SessionUserResponseOutDto> sessionUserListOut =
                SessionUserInquiryUseCase.getSessionUserOutDtoBySessionUuid(uuid);
        // 최대 신청인원수 + 멘티중복신청 검사
        domain.isMenteeValid(sessionUserListOut, dto.getMenteeUuid(), sessionResponseOut.getMaxHeadCount());
        // 세션 참가 신청 start
        // todo : 여기서 참가자리스트 테이블에 Insert (+1)
        AfterSessionUserOutDto afterSessionUserOutDto =
                sessionUserRepositoryOutPort.registerSessionUser(RegisterSessionOutDto.from(domain));
        // 세션 참가 후 최대정원 다 찼는지 확인
        Boolean closedSession = domain.isClosedSession(sessionUserListOut.size(), sessionResponseOut.getMaxHeadCount());
        afterSessionUserOutDto.setMentoringName(dto.getMentoringName());
        afterSessionUserOutDto.setIsClosed(closedSession);
        log.info("afterSessionUserOutDto: {}", afterSessionUserOutDto);
        // 정원 다 찼으면 세션 command table update

        // todo : 참가 후 정원 초과하면 세션 닫음 상태로 업데이트
        if(closedSession) mentoringServiceCallUseCase.closeSession(uuid);

        // "세션 참가등록" 메시지 발행
        sendMessageUseCase.sendRegisterSessionUserMessage("register-session-user", afterSessionUserOutDto);
    }

}
