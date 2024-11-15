package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.application.port.in.dto.RegisterSessionDto;

public interface SessionRockUseCase {
    void registerSessionUser(RegisterSessionDto sessionRequestDto) throws InterruptedException;
}
