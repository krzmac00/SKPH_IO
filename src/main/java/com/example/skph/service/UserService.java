package com.example.skph.service;
import com.example.skph.model.User;
import com.example.skph.model.users.Organization;
import com.example.skph.repository.UserRepository;
import com.example.skph.model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String firstName, String lastName, String username, String password, UserRole role, String email, Organization organization) {
        String passwordHash = passwordEncoder.encode(password);
        User newUser = new User(firstName, lastName, username, passwordHash, role, email, organization);

        try {
            return userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("An error occurred during registration!");
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Przykładowa metoda, jeśli potrzebujesz
     * walidacji roli użytkownika lub innych operacji.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
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

    public boolean validateOldPassword(String username, String oldPassword) {
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    // Metoda do resetowania hasła
    public User resetPassword(String username, String newPassword) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ChangeSetPersister.NotFoundException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));  // Kodowanie nowego hasła
        return userRepository.save(user);
    }
    //public Account activateAccount(Long id) {
    //Account account = accountRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Account not found!"));

    //account.setActive(true);

    //return accountRepository.save(account);
    //}

    //public Account deactivateAccount(Long id) {
    //Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found!"));

    //account.setActive(false);

    //return accountRepository.save(account);
    // }
    //public List<String> searchRoles(String query) {
    //List<Role> roles = userRepository.findDistinctRoles(query);
    //return roles.stream()
    //.map(Role::name)  // Konwertuje enum na String
    //.collect(Collectors.toList());
    // }

}
