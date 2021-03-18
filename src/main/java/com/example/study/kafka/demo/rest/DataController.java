package com.example.study.kafka.demo.rest;

import com.example.study.kafka.demo.dto.User;
import com.example.study.kafka.demo.service.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataController {
    private KafkaService service;
    public DataController(KafkaService service) {
        this.service = service;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> sendUserData(@RequestBody User user) {
        service.sendMessage(user);
        return ResponseEntity.ok("Ok!");
    }

    @GetMapping(value = "/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @GetMapping(value= "/user/{userId}")
    public ResponseEntity<String> user(@PathVariable String userId) {
        service.fetchUserData(userId);
        return ResponseEntity.ok("Ok!");
    }
}
