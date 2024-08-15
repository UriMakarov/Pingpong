package com.colvir.pingpong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PongService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "ping.out", groupId = "group_id")
    public void listenPing(String message) {
        System.out.println("Received: " + message);
        String pongId = UUID.randomUUID().toString();
        String pongDateTime = LocalDateTime.now().toString();
        String pongMessage = String.format("{\"pong_id\":\"%s\", \"pong_date_time\":\"%s\"}", pongId, pongDateTime);
        kafkaTemplate.send("pong.out", pongMessage);
        System.out.println("Sent: " + pongMessage);
    }
}
