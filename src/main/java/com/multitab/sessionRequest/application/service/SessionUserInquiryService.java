package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.application.port.in.SessionUserInquiryUseCase;
import com.multitab.sessionRequest.application.port.out.SessionUserRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.dto.SessionUserResponseOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class SessionUserInquiryService implements SessionUserInquiryUseCase {
    private final SessionUserRepositoryOutPort sessionUserRepositoryOutPort;

    @Override
    public List<SessionUserResponseOutDto> getSessionUserOutDtoBySessionUuid(String sessionUuid) {
        return sessionUserRepositoryOutPort.getSessionsUserBySessionUuid(sessionUuid);
    }
}
