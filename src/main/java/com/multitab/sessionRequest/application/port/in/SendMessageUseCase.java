package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;

public interface SendMessageUseCase {
    void sendRegisterSessionUserMessage(String topic , AfterSessionUserOutDto afterSessionUserOutDto);

}
