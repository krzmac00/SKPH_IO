package com.example.skph.model;

// Abstrakcyjna klasa reprezentująca zasób w systemie.
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.users.AidOrganization;
import com.example.skph.model.users.Organization;
import com.example.skph.model.victimRequest.Request;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "resource")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Resource {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identyfikator zasobu.

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "resource")
    Set<RequestResource> resourceList;

    @Getter
    @Setter
    public int amount;

    @Getter
    @Enumerated(EnumType.STRING)
    private ResourceStatus status; // Status zasobu.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aid_organization_id")
    private Organization assignedOrganization; // Organizacja przypisana do zasobu.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task assignedTask; // Zadanie przypisane do zasobu.

    private LocalDateTime createdAt; // Data utworzenia zasobu.

    private LocalDateTime updatedAt; // Data ostatniej aktualizacji zasobu.

    @ManyToOne
    private Request request;

    @Getter
    @Setter
    public boolean toGive; //to differentiate between what we have and what is needed


    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now(); // Ustawia datę utworzenia przed zapisaniem.
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(); // Ustawia datę aktualizacji przed zapisaniem.
    }

    // Przypisuje zasób do zadania, zmieniając jego status.
    public void assignTask(Task task) {
        if (status == ResourceStatus.AVAILABLE) {
            this.assignedTask = task;
            status = ResourceStatus.IN_USE;
        } else {
            throw new IllegalStateException("Resource is not available for assignment.");
        }
    }

    // Abstrakcyjna metoda sprawdzająca dostępność zasobu, implementowana przez klasy dziedziczące.
    public abstract boolean isAvailable();
}
