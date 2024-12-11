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
@Table(name = "\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @JsonDeserialize(using = UserRoleDeserializer.class)
    @Column(name = "role")
    private UserRole role;

    private String email;
    private String organization;

    public User(String firstName, String lastName, String username, String password, UserRole role, String email, String organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.organization = organization;
    }

}
