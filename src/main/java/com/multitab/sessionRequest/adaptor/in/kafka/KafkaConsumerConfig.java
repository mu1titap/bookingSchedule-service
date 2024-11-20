package com.multitab.sessionRequest.adaptor.in.kafka;
import com.multitab.sessionRequest.adaptor.in.kafka.dto.DeadlinePastSessionResponseOutDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${kafka.cluster.uri}")
    private String kafkaClusterUri;

    // 멘토링 생성 완료
    @Bean
    public ConsumerFactory<String, DeadlinePastSessionResponseOutDto> deadlinePastSessionConsumerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaClusterUri);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-sessionRequest-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(DeadlinePastSessionResponseOutDto.class, false));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeadlinePastSessionResponseOutDto> deadlinePastSessionListener() {
        ConcurrentKafkaListenerContainerFactory<String, DeadlinePastSessionResponseOutDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(deadlinePastSessionConsumerFactory());
        return factory;
    }



}