package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.CancelSessionUserMessage;
import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;

public interface SendMessageOutPort {
    void sendRegisterSessionUserMessage(String topic, AfterSessionUserOutDto dto);

    void sendCancelRegisterSessionUserMessage(String topic, CancelSessionUserMessage dto);

    void sendReRegisterSessionUserMessage(String topic, ReRegisterSessionUserMessage dto);
}
