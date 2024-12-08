package com.example.skph.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinTable(name = "requester")
    @NotNull
    @Getter
    @Setter
    private Requester requester;

    @ManyToOne
    @JoinTable(name = "address")
    @NotNull
    @Getter
    @Setter
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @NotNull
    @Getter
    private ArrayList<Task> taskList = new ArrayList<>();

    @Column
    @NotNull
    @Getter
    private boolean accomplished;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private ArrayList<Resource> resourceList = new ArrayList<>();

    @Column
    @NotNull
    @Getter
    private LocalDate startDate;

    @Column
    @Getter
    private LocalDate endDate;

    private ArrayList<Task> generateTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Resource resource : resourceList) {
            ArrayList<String> status = new ArrayList<>();
            int resourceAmount = resource.getAmount();
            for (int i = 0; i < resourceAmount + 1; i++) {
                //change instead of last day giving final status, it will have 1 additional "day" that will represent that
                status.add("created");
            }
            tasks.add(new Task(resource, status));
        }
        return tasks;
    }

    public Request(Requester requester, Address address,  ArrayList<Resource> resourceList, LocalDate endDate) {
        this.requester = requester;
        this.address = address;
        this.taskList = generateTasks();
        this.accomplished = false;
        this.resourceList = resourceList;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public Request() {
    }

    public boolean accomplishedCheck() {
        boolean accomplished = true;
        for (Task task : this.getTaskList()) {
            String status = task.getDaysList().getLast();
            if (status.equals("created") || status.equals("pending") || status.equals("inProgress")) {
                accomplished = false;
            }
        }
        return accomplished;
    }
}
