package com.colvir.pingpong.controller;

import com.colvir.pingpong.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("ping")
public class PingController {


    @Autowired
    private PingService pingService;

    @GetMapping
    public void sendPing() {
        pingService.sendPing();
    }
}