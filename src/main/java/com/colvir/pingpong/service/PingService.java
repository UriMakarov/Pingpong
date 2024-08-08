package com.colvir.pingpong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PingService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendPing() {
        String pingId = UUID.randomUUID().toString();
        String pingDateTime = LocalDateTime.now().toString();
        String message = String.format("{\"ping_id\":\"%s\", \"ping_date_time\":\"%s\"}", pingId, pingDateTime);
        kafkaTemplate.send("ping.out", message);
        System.out.println("Sent: " + message);
    }
    @KafkaListener(topics = "pong.out", groupId = "group_id")
    public void listenPong(String message) {
        System.out.println("Received: " + message);
    }
}