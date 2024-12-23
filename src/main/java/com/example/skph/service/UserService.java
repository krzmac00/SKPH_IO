package com.example.skph.service;
import com.example.skph.model.User;
import com.example.skph.model.users.Organization;
import com.example.skph.repository.UserRepository;
import com.example.skph.model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String firstName, String lastName, String username, String passwordHash, UserRole role, String email, String organizationName) {

        Organization organization = new Organization();
        organization.setName(organizationName);

        User newUser = new User(firstName, lastName, username, passwordHash, role, email, organization);

        try {
            return userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username already exists!");
        }
    }

}
