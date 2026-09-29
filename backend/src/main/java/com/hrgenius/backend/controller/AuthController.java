package com.hrgenius.backend.controller;

import com.hrgenius.backend.dto.LoginRequest;
import com.hrgenius.backend.dto.RegisterRequest;
import com.hrgenius.backend.entity.User;
import com.hrgenius.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest request) {

        User user = userService.registerUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );

        return Map.of(
                "message", "User registered successfully",
                "userId", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail()
        );
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {

        boolean valid = userService.verifyPassword(
                request.getUsername(),
                request.getPassword()
        );

        if (!valid) {
            return Map.of(
                    "message", "Invalid username or password"
            );
        }

        return Map.of(
                "message", "Login successful",
                "username", request.getUsername()
        );
    }
}