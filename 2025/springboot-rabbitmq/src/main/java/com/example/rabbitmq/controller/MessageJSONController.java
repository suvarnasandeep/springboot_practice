package com.example.rabbitmq.controller;

import com.example.rabbitmq.dto.User;
import com.example.rabbitmq.publisher.RabbitMQJsonProducer;
import com.example.rabbitmq.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageJSONController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    public MessageJSONController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        rabbitMQJsonProducer.sendMessage(user);
        return ResponseEntity.ok("JSON Message sent to RabbitMQ: " + user.toString());
    }
}
