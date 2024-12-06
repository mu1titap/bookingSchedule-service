package com.multitab.sessionRequest.adaptor.out.mysql.persistence;

import com.multitab.sessionRequest.adaptor.out.mysql.repository.SessionUserDslRepository;
import com.multitab.sessionRequest.adaptor.out.mysql.repository.SessionUserJpaRepository;
import com.multitab.sessionRequest.application.port.out.CheckSessionUserValidityStatusOutPort;
import com.multitab.sessionRequest.application.port.out.SessionUserInqueryRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.SessionUserRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.SessionUserStatusManagementOutPort;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.CancelSessionOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.ReRegisterSessionOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.RegisterSessionOutDto;
import com.multitab.sessionRequest.domain.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Component("SessionUserMysqlAdapter")
public class SessionUserMysqlAdapter implements SessionUserRepositoryOutPort , CheckSessionUserValidityStatusOutPort,
        SessionUserInqueryRepositoryOutPort, SessionUserStatusManagementOutPort {
    private final SessionUserJpaRepository sessionUserJpaRepository;
    private final SessionUserDslRepository sessionUserDslRepository;

    /**
     * SessionUserRepositoryOutPort
     */
    @Override
    public List<SessionUserResponseOutDto> getSessionsUserBySessionUuid(String sessionUuid, Status status) {
        return SessionUserResponseOutDto
                .from(sessionUserJpaRepository.findAllBySessionUuidAndStatus(sessionUuid, status));
    }

    @Override
    public Integer getCountBySessionUsers(String sessionUuid, Status status) {
        return sessionUserJpaRepository.countBySessionUuidAndStatus(sessionUuid, status);
    }

    @Override
    public AfterSessionUserOutDto registerSessionUser(RegisterSessionOutDto registerSessionOutDto) {
        return AfterSessionUserOutDto.from(sessionUserJpaRepository.save(registerSessionOutDto.toJpaEntity()));
    }

    @Override
    public Integer reRegisterSessionUser(ReRegisterSessionOutDto reRegisterSessionOutDto) {
        return sessionUserJpaRepository.updateSessionUser(reRegisterSessionOutDto.getStatus(), reRegisterSessionOutDto.getSessionUserId());
    }


    @Override
    public Integer cancelSessionUser(CancelSessionOutDto cancelSessionOutDto) {
        return sessionUserJpaRepository.updateSessionUser(cancelSessionOutDto.getStatus(), cancelSessionOutDto.getSessionUserId());
    }

    @Override
    public SessionUserResponseOutDto getSessionUserBySessionUuidAndMenteeUuid(String sessionUuid, String menteeUuid) {
        return sessionUserJpaRepository.findOneByMenteeUuidAndSessionUuid(menteeUuid, sessionUuid)
                .map(SessionUserResponseOutDto::from)
                .orElse(null);
    }


    @Override
    public List<SessionUserResponseOutDto> getPendingSessionUser(String sessionUuid) {
        return sessionUserDslRepository.getPendingSessionUser(sessionUuid);
    }

    @Override
    public List<SessionUserResponseOutDto> getConfirmedSessionUser(String sessionUuid) {
        return sessionUserDslRepository.getConfirmedSessionUser(sessionUuid);
    }

    @Override
    public void deadlineUpdateSessionUserStatus(List<String> sessionUserIdList, boolean sessionIsConfirmed) {
        sessionUserDslRepository.deadlineUpdateSessionUserStatus(sessionUserIdList, sessionIsConfirmed);
    }


    /**
     * CheckSessionUserValidityStatusOutPort
     */
    @Override
    public boolean checkSessionUserValidityStatus(String sessionUuid, String userUuid) {
        return sessionUserDslRepository.checkSessionUserValidityStatus(sessionUuid, userUuid);
    }

    @Override
    public void endSessionUserState(List<String> sessionUserIdList) {
        sessionUserDslRepository.updateStatusEndSessionUser(sessionUserIdList);
    }
}
