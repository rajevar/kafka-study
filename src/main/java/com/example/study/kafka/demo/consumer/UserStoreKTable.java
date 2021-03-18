package com.example.study.kafka.demo.consumer;

import com.example.study.kafka.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Slf4j
public class UserStoreKTable {
    @Bean
    public Function<KStream<String, User>, KTable<String,User>> userStore() {
        return redoStream ->
                redoStream.peek((k,v) -> log.info("k: {}, v: {}", k, v))
                        .toTable(Materialized.<String,User, KeyValueStore<Bytes, byte[]>>as("User.store")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(new JsonSerde<>(User.class)));
    }
}
