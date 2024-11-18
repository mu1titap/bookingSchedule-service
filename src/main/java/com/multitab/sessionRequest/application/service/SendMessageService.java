package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.application.port.in.SendMessageUseCase;
import com.multitab.sessionRequest.application.port.out.SendMessageOutPort;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.CancelSessionUserMessage;
import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class SendMessageService implements SendMessageUseCase {
    private final SendMessageOutPort sendMessageOutPort;

    @Override
    public void sendRegisterSessionUserMessage(String topic, AfterSessionUserOutDto afterSessionUserOutDto) {
        sendMessageOutPort.sendRegisterSessionUserMessage(topic, afterSessionUserOutDto);
    }

    @Override
    public void sendCancelRegisterSessionUserMessage(String topic, CancelSessionUserMessage cancelSessionUserMessage) {
        sendMessageOutPort.sendCancelRegisterSessionUserMessage(topic, cancelSessionUserMessage);
    }

    @Override
    public void sendReRegisterSessionUserMessage(String topic, ReRegisterSessionUserMessage reRegisterSessionUserMessage) {
        sendMessageOutPort.sendReRegisterSessionUserMessage(topic, reRegisterSessionUserMessage);
    }
}