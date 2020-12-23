package com.example.study.kafka.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Slf4j
@Configuration
public class KafkaConfig {

    public static final String TOPIC_BLAH = "Blah.Topic";

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(ProducerFactory producerFactory) {
        return new KafkaTemplate<String,Object>(producerFactory);
    }

    @Bean
    public NewTopic crateBlahTopic() {
        log.info("going to crate topic...");
        return TopicBuilder.name(TOPIC_BLAH).build();
    }
}
