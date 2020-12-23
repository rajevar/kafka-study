package com.example.study.kafka.demo.rest;

import com.example.study.kafka.demo.dto.User;
import com.example.study.kafka.demo.service.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    private KafkaService service;
    public DataController(KafkaService service) {
        this.service = service;
    }

    @GetMapping(value = "user")
    public ResponseEntity sendUserData(@RequestBody User user) {
        service.sendMessage(user);
        return ResponseEntity.ok("Ok!");
    }

    @GetMapping(value = "ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok("pong");
    }
}
