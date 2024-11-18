package com.multitab.sessionRequest.adaptor.out.kafka.persistence;

import com.multitab.sessionRequest.application.port.out.SendMessageOutPort;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.CancelSessionUserMessage;
import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component("SendMessageKafkaAdapter")
public class SendMessageKafkaAdapter implements SendMessageOutPort {

    private final KafkaTemplate<String, AfterSessionUserOutDto> kafkaRegisterSessionTemplate;
    private final KafkaTemplate<String, CancelSessionUserMessage> kafkaCancelRegisterSessionUserTemplate;
    private final KafkaTemplate<String, ReRegisterSessionUserMessage> kafkaReRegisterSessionUserTemplate;

    @Override
    public void sendRegisterSessionUserMessage(String topic, AfterSessionUserOutDto dto) {
        try {
            kafkaRegisterSessionTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("register session user event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendCancelRegisterSessionUserMessage(String topic, CancelSessionUserMessage dto) {
        try {
            kafkaCancelRegisterSessionUserTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("cancel register session user event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendReRegisterSessionUserMessage(String topic, ReRegisterSessionUserMessage dto) {
        try {
            kafkaReRegisterSessionUserTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("re-register session user event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }
}
