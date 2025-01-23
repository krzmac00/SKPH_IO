package com.example.skph.model;

// Reprezentuje użytkownika w systemie.
import com.example.skph.model.enums.UserRole;
import com.example.skph.model.users.Organization;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identyfikator użytkownika.

    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;

    @Setter
    private String username; // Nazwa użytkownika
    private String passwordHash;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Organization organization;

    @Enumerated(EnumType.STRING)
    private UserRole role; // Rola w systemie


    public User(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public User(String firstName,
                String lastName,
                String username,
                String passwordHash,
                UserRole role,
                String email,
                Organization organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.email = email;
        this.organization = organization;
    }
}
