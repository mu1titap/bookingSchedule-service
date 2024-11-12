package com.multitab.sessionRequest.adaptor.out.kafka;

import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, AfterSessionUserOutDto> kafkaRegisterSessionTemplate;

    /**
     * 멘토링 생성 이벤트 발생
     */
    public void sendRegisterSessionUser(String topic, AfterSessionUserOutDto dto) {
        try {
            kafkaRegisterSessionTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("register session message send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

}