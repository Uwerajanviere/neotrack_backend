package org.spring.springsecurity.neotrackbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String health() {
        return "NeoTrack Backend is running! 🚀";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK - NeoTrack Backend is healthy";
    }
}
