//package com.multitab.sessionRequest.adaptor.out.kafka;
//
//import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
//import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;
//import com.multitab.sessionRequest.application.port.out.dto.out.SessionConfirmedMessage;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class KafkaProducer {
//    private final KafkaTemplate<String, AfterSessionUserOutDto> kafkaRegisterSessionUserTemplate;
//    private final KafkaTemplate<String, ReRegisterSessionUserMessage>  kafkaReRegisterSessionUserTemplate;
//    private final KafkaTemplate<String, SessionConfirmedMessage>  kafkaSessionConfirmedTemplate;
//    /**
//     * 세션 참가 등록 이벤트 발생
//     */
//    public void sendRegisterSessionUser(String topic, AfterSessionUserOutDto dto) {
//        try {
//            kafkaRegisterSessionUserTemplate.send(topic, dto);
//        }
//        catch (Exception e) {
//            log.info("register session user message send 실패 : " + e);
//            throw new RuntimeException(e);
//        }
//    }
//    /**
//     * 세션 참가 '재' 등록 이벤트 발생
//     */
//    public void sendReRegisterSessionUser(String topic, ReRegisterSessionUserMessage dto) {
//        try {
//            kafkaReRegisterSessionUserTemplate.send(topic, dto);
//        }
//        catch (Exception e) {
//            log.info("re-register session user message send 실패 : " + e);
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 세션 확정 이벤트 발생
//     */
//    public void sendSessionConfirmed(String topic, SessionConfirmedMessage dto) {
//        try {
//            kafkaSessionConfirmedTemplate.send(topic, dto);
//        }
//        catch (Exception e) {
//            log.info("session confirmed message send 실패 : " + e);
//            throw new RuntimeException(e);
//        }
//    }
//}