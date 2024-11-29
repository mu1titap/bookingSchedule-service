package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.domain.Status;

import java.util.List;

public interface SessionUserInqueryRepositoryOutPort {
    List<SessionUserResponseOutDto> getSessionsUserBySessionUuid(String sessionUuid, Status status);
    Integer getCountBySessionUsers(String sessionUuid, Status status);
    SessionUserResponseOutDto getSessionUserBySessionUuidAndMenteeUuid(String sessionUuid, String menteeUuid);
    List<SessionUserResponseOutDto> getPendingSessionUser(String sessionUuid);
    List<SessionUserResponseOutDto> getConfirmedSessionUser(String sessionUuid);
}
