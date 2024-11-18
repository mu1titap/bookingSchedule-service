package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.application.port.in.dto.CancelSessionDto;

public interface CancelSessionUserUseCase {
    void cancelSessionUser(CancelSessionDto cancelSessionDto);
}
