package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.application.port.out.dto.out.*;

public interface SendMessageOutPort {
    void sendRegisterSessionUserMessage(String topic, AfterSessionUserOutDto dto);

    void sendCancelRegisterSessionUserMessage(String topic, CancelSessionUserMessage dto);

    void sendReRegisterSessionUserMessage(String topic, ReRegisterSessionUserMessage dto);

    void sendConfirmSessionMessage(String topic, SessionConfirmedMessage sessionConfirmedMessage);

    void sendUpdateSessionUserMessage(String topic, SessionUserUpdateMessage sessionUserUpdateMessage);

    void sendEndSessionMessage(String topic, EndSessionMessage endSessionMessage);
}
