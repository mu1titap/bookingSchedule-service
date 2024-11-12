package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.application.port.in.dto.RegisterSessionDto;

public interface RegisterSessionUserUseCase {
    void registerSessionUser(RegisterSessionDto sessionRequestDto);
}
