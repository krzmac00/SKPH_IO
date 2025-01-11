package com.example.skph.model;

import com.example.skph.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "resource")
    private Resource resource;

    /*@NotNull
    @Getter
    @Setter
    private boolean accomplished;*/

    @NotNull
    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "days_list", joinColumns = @JoinColumn(name = "task_id"))
    private List<Day> daysList;
    //status: created, pending, inProgress, completed, closed, canceled

    @ManyToOne
    @JoinColumn(name = "request_id") //resource department use it to access address of task;
    private Request request;

    public Task() {
    }

    public Task(Resource resource, List<Day> daysList) {
        this.resource = resource;
        this.daysList = daysList;
    }
}
