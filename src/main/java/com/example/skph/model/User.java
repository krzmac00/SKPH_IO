package com.example.skph.model;

import com.example.skph.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }
}