package com.example.skph.model;

import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.users.AidOrganization;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "resource")
@Inheritance(strategy = InheritanceType.JOINED)
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    public int amount;

    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @ManyToOne
    private AidOrganization assignedOrganization;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void assignTask(Task task) {
        // Implementacja przypisania zadania
    }
}