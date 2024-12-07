package com.example.skph.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Getter
    @Setter
    private Requester requester;

    @Column
    @NotNull
    @Getter
    @Setter
    private Address address;

    @NotNull
    @Getter
    @Setter
    private ArrayList<Task> taskList = new ArrayList<>();

    @Column
    @NotNull
    @Getter
    private boolean accomplished;

    @NotNull
    private ArrayList<Resource> resourceList = new ArrayList<>();

    @Column
    @NotNull
    private LocalDate startDate;

    @Column
    //@NotNull
    private LocalDate endDate;

    private ArrayList<Task> generateTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Resource resource : resourceList) {
            ArrayList<String> status = new ArrayList<>();
            int resourceAmount = resource.getAmount();
            for (int i = 0; i < resourceAmount + 1; i++) {
                //change instead of last day giving final status, it will have 1 additional "day" that will represent that
                status.add("undone");
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

    public boolean accomplishedCheck() {
        boolean accomplished = true;
        for (Task task : this.getTaskList()) {
            String status = task.getDaysList().getLast();
            if (status.equals("undone") || status.equals("pending") || status.equals("partiallyCompleted")) {
                accomplished = false;
            }
        }
        return accomplished;
    }
}
