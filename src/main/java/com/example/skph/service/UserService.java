package com.example.skph.service;
import com.example.skph.model.Role;
import com.example.skph.model.User;
import com.example.skph.repository.UserRepository;
import com.example.skph.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.skph.config.PasswordConfig;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new IllegalArgumentException("An error occurred during registration!");
        }
    }
    public Boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("Invalid username or password!");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password!");
        }
        Boolean isCorrect = passwordEncoder.matches(password,user.getPassword());
        if (isCorrect) {
            userRepository.save(user);
        }
        return isCorrect;
    }
    //public List<String> searchRoles(String query) {
        //List<Role> roles = userRepository.findDistinctRoles(query);
        //return roles.stream()
                //.map(Role::name)  // Konwertuje enum na String
                //.collect(Collectors.toList());
   // }
}
