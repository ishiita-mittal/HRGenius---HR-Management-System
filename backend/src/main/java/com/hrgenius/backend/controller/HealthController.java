package com.hrgenius.backend.controller;

import com.hrgenius.backend.service.DatabaseHealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    private final DatabaseHealthService databaseHealthService;

    public HealthController(DatabaseHealthService databaseHealthService) {
        this.databaseHealthService = databaseHealthService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "application", "HRGenius Backend"
        );
    }

    @GetMapping("/health/db")
    public Map<String, String> databaseHealth() {

        boolean connected = databaseHealthService.isDatabaseConnected();

        return Map.of(
                "status", connected ? "UP" : "DOWN",
                "database", "Oracle",
                "message", connected
                        ? "Database connection successful"
                        : "Database connection failed"
        );
    }
}