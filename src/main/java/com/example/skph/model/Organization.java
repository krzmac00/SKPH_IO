package com.example.skph.model;

// Reprezentuje organizację w systemie.
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identyfikator organizacji.

    private String name; // Nazwa organizacji.

    private String type; // Typ organizacji (np. NGO, fundacja, rządowa).

    private String contactInfo; // Dane kontaktowe organizacji.
}
