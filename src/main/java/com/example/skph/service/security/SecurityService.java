package com.example.skph.service.security;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    public String encryptPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String generateToken() {
        return "sample-token-" + System.currentTimeMillis();
    }

    public void logEvent(Long userId, String event) {
        System.out.println("Log: User " + userId + " performed event: " + event);
    }
}
