package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;

import java.util.List;

public interface SessionUserStatusManagementOutPort {

    void deadlineUpdateSessionUserStatus(List<String> sessionUserIdList, boolean sessionIsConfirmed);
    void endSessionUserState(List<String> confirmedSessionUserList);
}
