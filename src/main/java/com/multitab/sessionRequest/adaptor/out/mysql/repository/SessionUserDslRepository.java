package com.multitab.sessionRequest.adaptor.out.mysql.repository;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;

import java.util.List;

public interface SessionUserDslRepository {
    List<SessionUserResponseOutDto> getPendingSessionUser(String sessionUuid);

    void updateSessionUserStatus(List<String> sessionUserIdList, boolean sessionIsConfirmed);
}
