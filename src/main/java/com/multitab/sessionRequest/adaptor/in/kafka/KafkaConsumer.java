package com.multitab.sessionRequest.adaptor.in.kafka;

import com.multitab.sessionRequest.adaptor.in.kafka.dto.DeadlinePastSessionResponseOutDto;
import com.multitab.sessionRequest.application.port.in.DeadlinePastSessionUpdateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaConsumer {
    private final DeadlinePastSessionUpdateUseCase deadlinePastSessionUpdateUseCase;

    /**
     *  마감 세션 이벤트 컨슘
     */
    @KafkaListener(topics = "deadline-past-session", groupId = "kafka-sessionRequest-service",
            containerFactory = "deadlinePastSessionListener")
    public void createMentoring(DeadlinePastSessionResponseOutDto dto) {

        log.info("마감 세션 이벤트 컨슘 : {}", dto);
        deadlinePastSessionUpdateUseCase.updateSessionUser(dto);

    }



}

