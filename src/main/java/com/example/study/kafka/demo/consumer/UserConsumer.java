package com.example.study.kafka.demo.consumer;

import com.example.study.kafka.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class UserConsumer {

    @Bean
    public Consumer<KStream<?, User>> userConsume() {
        return userKStream ->
                    userKStream.peek((k,v) -> log.info(" {} : value - {}", k, v));
    }
}
