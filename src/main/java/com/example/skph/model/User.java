package com.example.skph.model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.example.skph.enums.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "UserRole")
    @JsonDeserialize(using = UserRoleDeserializer.class)
    private UserRole role;

    private String email;
    private String organization;

    public User(String firstName, String lastName, String username, String passwordHash, UserRole role, String email, String organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.email = email;
        this.organization = organization;
    }

}
