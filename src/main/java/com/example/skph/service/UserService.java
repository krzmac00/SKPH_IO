package com.example.skph.service;
import com.example.skph.model.User;
import com.example.skph.repository.UserRepository;
import com.example.skph.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.skph.config.PasswordConfig;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String firstName, String lastName, String username, String password, UserRole role, String email, String organization) {
        String passwordHash = passwordEncoder.encode(password);
        User newUser = new User(firstName, lastName, username, passwordHash, role, email, organization);

        try {
            return userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username already exists!");
        }
    }
    public Boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        Boolean isCorrect = passwordEncoder.matches(password, user.getPassword());

        if (isCorrect) {
            return true;
        } else {
            return false;
        }
    }
}
