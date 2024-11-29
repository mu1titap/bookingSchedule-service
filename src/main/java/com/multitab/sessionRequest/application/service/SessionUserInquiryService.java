package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.application.port.in.SessionUserInquiryUseCase;
import com.multitab.sessionRequest.application.port.out.SessionUserInqueryRepositoryOutPort;
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
@Transactional(readOnly = true)
public class SessionUserInquiryService implements SessionUserInquiryUseCase {
    private final SessionUserRepositoryOutPort sessionUserRepositoryOutPort;
    private final SessionUserInqueryRepositoryOutPort sessionUserInqueryRepositoryOutPort;


    @Override
    public List<SessionUserResponseOutDto> getSessionUserOutDtoBySessionUuid(String sessionUuid, Status status) {
        return sessionUserInqueryRepositoryOutPort.getSessionsUserBySessionUuid(sessionUuid, status);
    }

    @Override
    public Integer getCountBySessionUsers(String sessionUuid, Status status) {
        return sessionUserInqueryRepositoryOutPort.getCountBySessionUsers(sessionUuid, status);
    }

    @Override
    public SessionUserResponseOutDto getSessionUserOutDtoBySessionUuidAndMenteeUuid(String sessionUuid, String menteeUuid) {
        return sessionUserInqueryRepositoryOutPort.getSessionUserBySessionUuidAndMenteeUuid(sessionUuid, menteeUuid);
    }



}
