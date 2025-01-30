package com.example.skph.model;

// Reprezentuje zadanie w systemie.
import com.example.skph.model.enums.TaskStatus;
import com.example.skph.model.users.Organization;
import com.example.skph.model.users.Volunteer;
import com.example.skph.model.victimRequest.Request;
import com.example.skph.enums.Status;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String review;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.CREATED;

    //    @OneToOne
    //    private Location location;

    @ManyToOne
    Organization organization;

    @ManyToOne
    private Volunteer volunteer;

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

    @Getter
    @ManyToOne
    @JoinColumn(name = "request_id") //resource department use it to access address of task;
    private Request request;


    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Day> statusHistory;
    //private DaysList daysList;
    //status: created, pending, inProgress, completed, closed, canceled

    public Task() {
        createdAt = LocalDateTime.now();
    }

    public Task(TaskStatus status, String review, Organization organization, Volunteer volunteer) {
        this.status = status;
        this.review = review;
        this.organization = organization;
        this.volunteer = volunteer;
    }

    // Przypisuje zasób do zadania.
    public void assignResource(Resource resource) {
        assignedResources.add(resource);
        resource.assignTask(this);
    }

    public Task(Request request) {
        this.request = request;
    }

    public void releaseResource(Resource resource) {
        assignedResources.remove(resource);
    }

    public void setStatus(Status status) { //or a number i dont know
        int newDay = statusHistory.getLast().getDayIndex() + 1;
        Day day = new Day(status, newDay);
        this.statusHistory.add(day);
    }
}
