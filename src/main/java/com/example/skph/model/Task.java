package com.example.skph.model;

import com.example.skph.model.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.CREATED;

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

    public Task() {
        createdAt = LocalDateTime.now();
    }

    public void assignResource(Resource resource) {
        assignedResources.add(resource);
        resource.assignTask(this);
    }

    public void releaseResource(Resource resource) {
        assignedResources.remove(resource);
    }


    public void setStatus(TaskStatus status) {
        this.updatedAt = LocalDateTime.now();
        this.status = status;
    }
}
