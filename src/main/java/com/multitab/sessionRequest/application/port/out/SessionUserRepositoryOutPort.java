package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.application.port.out.dto.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.RegisterSessionOutDto;

import java.util.List;

public interface SessionUserRepositoryOutPort {
    List<SessionUserResponseOutDto> getSessionsUserBySessionUuid(String sessionUuid);

    AfterSessionUserOutDto registerSessionUser(RegisterSessionOutDto registerSessionOutDto);
}
