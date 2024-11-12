package com.multitab.sessionRequest.adaptor.out.kafka.persistence;

import com.multitab.sessionRequest.application.port.out.SendMessageOutPort;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component("SendMessageKafkaAdapter")
public class SendMessageKafkaAdapter implements SendMessageOutPort {

    private final KafkaTemplate<String, AfterSessionUserOutDto> kafkaRegisterSessionTemplate;


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
}
