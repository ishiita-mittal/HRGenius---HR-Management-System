package com.hrgenius.backend.service;

import com.hrgenius.backend.entity.User;
import com.hrgenius.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createTestUser() {

        User user = new User();

        user.setUsername("testuser");
        user.setEmail("testuser@hrgenius.com");
        user.setPasswordHash("temporary-test-hash");

        return userRepository.save(user);
    }
}