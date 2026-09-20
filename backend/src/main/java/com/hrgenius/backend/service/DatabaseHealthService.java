package com.hrgenius.backend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseHealthService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseHealthService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isDatabaseConnected() {
        Integer result = jdbcTemplate.queryForObject(
                "SELECT 1 FROM DUAL",
                Integer.class
        );

        return result != null && result == 1;
    }
}