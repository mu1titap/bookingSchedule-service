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
import com.multitab.sessionRequest.domain.model.sessionRequestDomain;
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
    private final SessionUserInquiryUseCase SessionUserInquiryUseCase;
    private final SessionUserRepositoryOutPort sessionUserRepositoryOutPort;
    private final SendMessageUseCase sendMessageUseCase;
    @Override
    public void registerSessionUser(RegisterSessionDto dto) {
        String uuid = dto.getSessionUuid();
        // 세션 상태 확인
        SessionResponseOutDto sessionResponseOutDto = mentoringServiceCallUseCase.getSessionOutDtoByUuid(uuid);
        sessionRequestDomain domain =
                sessionRequestDomain.createSessionRequestDomain(dto.getSessionUuid(), dto.getMenteeUuid());
        // 예약 마감일 검사
        domain.isDeadlineValid(sessionResponseOutDto.getDeadlineDate());
        // 참가자 리스트 조회
        List<SessionUserResponseOutDto> sessionUserOutDtos =
                SessionUserInquiryUseCase.getSessionUserOutDtoBySessionUuid(uuid);
        // 최대 신청인원수 + 멘티중복신청 검사
        domain.isMenteeValid(sessionUserOutDtos, dto.getMenteeUuid(), sessionResponseOutDto.getMaxHeadCount());
        // 세션 참가 신청
        AfterSessionUserOutDto afterSessionUserOutDto =
                sessionUserRepositoryOutPort.registerSessionUser(RegisterSessionOutDto.from(domain));
        log.info("afterSessionUserOutDto : "+afterSessionUserOutDto);

        sendMessageUseCase.sendRegisterSessionUserMessage("register-session-user", afterSessionUserOutDto);

    }
}
