package com.example.study.kafka.demo.service;

import com.example.study.kafka.demo.KafkaConfig;
import com.example.study.kafka.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {
    private KafkaTemplate<String, Object> kafkaTemplate;

    KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        log.info("sending user : {}", user);
        kafkaTemplate.send(KafkaConfig.TOPIC_BLAH, user);
    }

    @KafkaListener(topics = KafkaConfig.TOPIC_BLAH, groupId = KafkaConfig.TOPIC_BLAH)
    public void topicListener(User user) {
        log.info("User : {}, {}, {}", user.getName(), user.getAge(), user.getLocation());
    }

//    @KafkaListener(topics = Constants.TOPIC_BEHAVE,groupId = Constants.TOPIC_BEHAVE, clientIdPrefix = "behave.1")
//    public void applyBehaviour(Behaviour b) {
//        log.info("Received behaviour {}, processing",b.getType());
//        b.exe();
//    }
}
