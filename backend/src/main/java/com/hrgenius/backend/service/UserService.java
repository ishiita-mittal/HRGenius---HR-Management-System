package com.hrgenius.backend.service;

import com.hrgenius.backend.entity.User;
import com.hrgenius.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public boolean verifyPassword(String username, String password) {

    Optional<User> userOptional = userRepository.findByUsername(username);

    if (userOptional.isEmpty()) {
        return false;
    }

    User user = userOptional.get();

    return passwordEncoder.matches(
            password,
            user.getPasswordHash()
    );
   }

    public User registerUser(
            String username,
            String email,
            String password) {

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);

        String hashedPassword = passwordEncoder.encode(password);
        user.setPasswordHash(hashedPassword);

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}