package com.multitab.sessionRequest.adaptor.out.kafka;

import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.CancelSessionUserMessage;
import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    // 세션 참가
    @Bean
    public ProducerFactory<String, AfterSessionUserOutDto> sessionUserProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        //configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-1:19092,kafka-2:19092,kafka-3:19092"); // 배포 버전
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, AfterSessionUserOutDto> kafkaRegisterSessionUserTemplate() {
        return new KafkaTemplate<>(sessionUserProducerFactory());
    }

    // 세션 참가 취소
    @Bean
    public ProducerFactory<String, CancelSessionUserMessage> cancelSssionUserProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, CancelSessionUserMessage> kafkaCancelRegisterSessionUserTemplate() {
        return new KafkaTemplate<>(cancelSssionUserProducerFactory());
    }

    // 세션 '재' 참가
    @Bean
    public ProducerFactory<String, ReRegisterSessionUserMessage> reRegisterSessionUserProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, ReRegisterSessionUserMessage> kafkaReRegisterSessionUserTemplate() {
        return new KafkaTemplate<>(reRegisterSessionUserProducerFactory());
    }
}
