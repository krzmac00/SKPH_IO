package com.example.skph.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToOne
//    private Location location;

    @OneToMany(mappedBy = "assignedTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> assignedResources = new ArrayList<>();

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

    public void assignResource(Resource resource) {
        assignedResources.add(resource);
        resource.assignTask(this);
    }

    public void releaseResource(Resource resource) {
        assignedResources.remove(resource);
    }
}
