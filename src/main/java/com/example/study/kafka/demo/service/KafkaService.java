package com.example.study.kafka.demo.service;

import com.example.study.kafka.demo.KafkaConfig;
import com.example.study.kafka.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.HostInfo;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KafkaService {
    private final  KafkaTemplate<String, Object> kafkaTemplate;
    private final InteractiveQueryService interactiveQueryService;
    private RestTemplate restTemplate = new RestTemplate();

    KafkaService(KafkaTemplate<String, Object> kafkaTemplate, InteractiveQueryService interactiveQueryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.interactiveQueryService = interactiveQueryService;
    }

    public void sendMessage(User user) {
        log.info("sending user : {}", user);
        kafkaTemplate.send(KafkaConfig.TOPIC_BLAH, user.getId(), user);
    }

    @KafkaListener(topics = KafkaConfig.TOPIC_BLAH, groupId = KafkaConfig.TOPIC_BLAH)
    public void topicListener(ConsumerRecord<String, User> record) {
        log.info("record: {} ", record);
    }

    public void fetchUserData(String userId) {
        ReadOnlyKeyValueStore<String, User> queryableStore =
                interactiveQueryService.getQueryableStore("User.store", QueryableStoreTypes.keyValueStore());
        KeyValueIterator<String, User> all = queryableStore.all();
        while (all.hasNext()) {
            final KeyValue<String, User> next = all.next();
            log.info("key: {}, value: {}", next.key, next.value);
        }

    }

}
