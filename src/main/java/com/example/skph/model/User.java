package com.example.skph.model;

// Reprezentuje użytkownika w systemie.
import com.example.skph.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identyfikator użytkownika.

    private String name; // Nazwa użytkownika.

    private String email; // Adres e-mail użytkownika.

    @Enumerated(EnumType.STRING)
    private UserRole role; // Rola użytkownika w systemie.
}
