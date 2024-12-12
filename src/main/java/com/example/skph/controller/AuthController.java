package com.example.skph.controller;

import com.example.skph.service.UserService;
import com.example.skph.constraints.Register;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Profile("dev")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String show() {
        return "Authentication";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid Register request) {
        try {
            userService.register(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getRole(),
                    request.getEmail(),
                    request.getOrganization()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}