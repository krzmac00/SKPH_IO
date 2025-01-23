package com.example.skph.controller;
import com.example.skph.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.skph.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final UserRepository userRepository;

    public OrganizationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<String> getOrganizations(@RequestParam(required = false) String query) {
        List<User> allUsers = userRepository.findAll(); // Pobierz wszystkich użytkowników
        List<String> organizations = new ArrayList<>();

        // Filtrowanie organizacji na podstawie roli
        for (User user : allUsers) {
            if ("AID_ORGANIZATION".equals(user.getRole())) {
                organizations.add(user.getUsername());
            }
        }

        // Opcjonalne filtrowanie na podstawie query
        if (query != null && !query.isEmpty()) {
            List<String> filteredOrganizations = new ArrayList<>();
            for (String org : organizations) {
                if (org.toLowerCase().contains(query.toLowerCase())) {
                    filteredOrganizations.add(org);
                }
            }
            return filteredOrganizations;
        }

        return organizations;
    }
}