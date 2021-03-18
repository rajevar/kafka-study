package com.example.study.kafka.demo.consumer;

import com.example.study.kafka.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Profile(value = "!test")
@Slf4j
public class SomeConsumer {

    @Bean
    public Consumer<KStream<?, User>> processUser() {
        return processNdcStream ->
                processNdcStream.foreach((k,v)-> log.info("{} value: {}", k , v));
    }
}
