package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.application.port.in.SessionUserInquiryUseCase;
import com.multitab.sessionRequest.application.port.out.SessionUserRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.domain.Status;
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
    public List<SessionUserResponseOutDto> getSessionUserOutDtoBySessionUuid(String sessionUuid, Status status) {
        return sessionUserRepositoryOutPort.getSessionsUserBySessionUuid(sessionUuid, status);
    }

    @Override
    public Integer getCountBySessionUsers(String sessionUuid, Status status) {
        return sessionUserRepositoryOutPort.getCountBySessionUsers(sessionUuid, status);
    }

    @Override
    public SessionUserResponseOutDto getSessionUserOutDtoBySessionUuidAndMenteeUuid(String sessionUuid, String menteeUuid) {
        return sessionUserRepositoryOutPort.getSessionUserBySessionUuidAndMenteeUuid(sessionUuid, menteeUuid);
    }



}
