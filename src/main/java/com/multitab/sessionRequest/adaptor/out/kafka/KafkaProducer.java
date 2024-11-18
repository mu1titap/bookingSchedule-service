package com.multitab.sessionRequest.adaptor.out.kafka;

import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, AfterSessionUserOutDto> kafkaRegisterSessionUserTemplate;
    private final KafkaTemplate<String, ReRegisterSessionUserMessage>  kafkaReRegisterSessionUserTemplate;
    /**
     * 세션 참가 등록 이벤트 발생
     */
    public void sendRegisterSessionUser(String topic, AfterSessionUserOutDto dto) {
        try {
            kafkaRegisterSessionUserTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("register session user message send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    public void sendReRegisterSessionUser(String topic, ReRegisterSessionUserMessage dto) {
        try {
            kafkaReRegisterSessionUserTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("re-register session user message send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

}