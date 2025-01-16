package com.example.skph.model;

// Reprezentuje zadanie w systemie.
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identyfikator zadania.

    @OneToMany(mappedBy = "assignedTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> assignedResources = new ArrayList<>(); // Lista przypisanych zasobów.

    private LocalDateTime createdAt; // Data utworzenia zadania.

    private LocalDateTime updatedAt; // Data ostatniej aktualizacji zadania.

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now(); // Ustawia datę utworzenia przed zapisaniem.
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(); // Ustawia datę aktualizacji przed zapisaniem.
    }

    // Przypisuje zasób do zadania.
    public void assignResource(Resource resource) {
        assignedResources.add(resource);
        resource.assignTask(this);
    }
}
