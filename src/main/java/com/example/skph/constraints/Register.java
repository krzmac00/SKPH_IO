package com.example.skph.constraints;

import com.example.skph.model.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Register {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role name is required")
    private UserRole role;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Organization name is required")
    private String organization;
}
