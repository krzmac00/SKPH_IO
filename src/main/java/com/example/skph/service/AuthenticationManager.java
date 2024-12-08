package com.example.skph.service;

import com.example.skph.model.User;
import com.example.skph.repository.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    public User register(String username, String password, String role, String email, String organization) {
        String encryptedPassword = securityService.encryptPassword(password);
        User user = new User(username, encryptedPassword, role, email, organization);
        return userRepository.save(user);
    }

    public boolean login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            String encryptedPassword = securityService.encryptPassword(password);
            return userOpt.get().getPasswordHash().equals(encryptedPassword);
        }
        return false;
    }

    public void resetPassword(String email, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPasswordHash(securityService.encryptPassword(newPassword));
            userRepository.save(user);
        }
    }
}
