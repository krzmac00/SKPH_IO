package com.example.skph.controller;
import com.example.skph.model.PasswordResetRequest;
import ch.qos.logback.core.model.Model;
import com.example.skph.constraints.Login;
import com.example.skph.model.users.Organization;
import com.example.skph.repository.OrganizationRepository;
import com.example.skph.service.UserService;
import com.example.skph.constraints.Register;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@Profile("dev")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegister() {
        return "Register";
    }
    @GetMapping("/login")
    public String showLogin() {
        return "Login";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid Register request) {
        try {
            Organization organization;
            List<Organization> organizations = organizationRepository.findAllWithName(request.getOrganization());

            if(organizations.isEmpty()) {
                organization = null;
            } else {
                organization = organizations.getFirst();
            }

            userService.register(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getRole(),
                    request.getEmail(),
                    organization
            );

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid Login request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.login(request.getUsername(), request.getPassword()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/change_password")
    public String showChangePasswd() {
        return "Change_Passwd";
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
        try {
            // Sprawdzanie czy stare hasło jest poprawne
            if (!userService.validateOldPassword(request.getUsername(), request.getOldPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect old password");
            }

            // Zmienianie hasła
            userService.resetPassword(request.getUsername(), request.getNewPassword());
            return ResponseEntity.ok("Password reset successful");
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resetting password");
        }
    }
    @GetMapping("/main")
    public String mainPage() {
        return "main"; // Widok main.html, w którym będziemy używać ${username}
    }

}


