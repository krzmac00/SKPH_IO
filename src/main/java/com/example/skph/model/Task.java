package com.example.skph.model;

// Reprezentuje zadanie w systemie.
import com.example.skph.model.enums.TaskStatus;
import com.example.skph.model.users.Organization;
import com.example.skph.model.users.Volunteer;
import com.example.skph.model.victimRequest.Request;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String review;

    //        this.updatedAt = LocalDateTime.now();
    @Setter
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.CREATED;

    //    @OneToOne
    //    private Location location;

    @ManyToOne
    Request request;

    @ManyToOne
    Organization organization;

    @ManyToOne
    private Volunteer volunteer;

    // Ewentualnie logika sprawdzania, czy resource jest dostępny
    /**
     * Relacja 1:1 z Resource:
     * - Jedno zadanie ma dokładnie jeden zasób
     * - Resource nie trzyma referencji do Task
     * -- SETTER --
     *  Metoda pomocnicza do przypisania zasobu do taska.
     *  Usuwamy listy i multiple resources – bo architekt zastrzegł 1:1.

     */
    @Setter
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    //    @OneToMany(mappedBy = "assignedTask", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Resource> assignedResources = new ArrayList<>(); // Lista przypisanych zasobów.
//
//    private LocalDateTime createdAt; // Data utworzenia zadania.
//
//    private LocalDateTime updatedAt; // Data ostatniej aktualizacji zadania.

//    @PrePersist
//    public void onCreate() {
//        createdAt = LocalDateTime.now(); // Ustawia datę utworzenia przed zapisaniem.
//    }
//
//    @PreUpdate
//    public void onUpdate() {
//        updatedAt = LocalDateTime.now(); // Ustawia datę aktualizacji przed zapisaniem.
//    }

    public Task() {
//        createdAt = LocalDateTime.now();
    }

    public Task(TaskStatus status, String review, Organization organization, Volunteer volunteer) {
        this.status = status;
        this.review = review;
        this.organization = organization;
        this.volunteer = volunteer;
    }

    public void assignResource(Resource resource) {
        if (!resource.isAvailable()) {
            throw new IllegalStateException("Resource is not available for assignment.");
        }
        this.resource = resource;
    }

//    // Przypisuje zasób do zadania.
//    public void assignResource(Resource resource) {
////        assignedResources.add(resource);
//        resource.assignTask(this);
//    }

//    public void releaseResource(Resource resource) {
//        assignedResources.remove(resource);
//    }

}
