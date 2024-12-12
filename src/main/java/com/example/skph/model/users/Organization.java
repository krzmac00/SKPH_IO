package com.example.skph.model.users;

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
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;  // Nazwa organizacji
    private String type;  // Typ organizacji (np. NGO, fundacja, rządowa)
    private String contactInfo;  // Dane kontaktowe organizacji
}
