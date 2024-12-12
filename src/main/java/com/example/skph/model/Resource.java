package com.example.skph.model;

import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.users.AidOrganization;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "resources")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
        if (status == ResourceStatus.AVAILABLE) {
            status = ResourceStatus.IN_USE;
        } else {
            throw new IllegalStateException("Resource is not available for assignment.");
        }
    }

    // Abstract method to be implemented by subclasses
    public abstract boolean isAvailable();
}

