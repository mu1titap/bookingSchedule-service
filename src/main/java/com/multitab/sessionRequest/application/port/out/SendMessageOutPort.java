package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;

public interface SendMessageOutPort {
    void sendRegisterSessionUserMessage(String topic, AfterSessionUserOutDto afterSessionUserOutDto);
}
