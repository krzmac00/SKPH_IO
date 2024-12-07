package com.example.skph.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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

    @NotNull
    @Getter
    @Setter
    private boolean accomplished;

    @ElementCollection
    @CollectionTable(name = "daysList")
    @NotNull
    @Getter
    private ArrayList<String> daysList = new ArrayList<>();
    //status: created, pending, inProgress, completed, closed, canceled

    public Task() {
    }

    public Task(Resource resource, ArrayList<String> daysList) {
        this.resource = resource;
        this.accomplished = false;
        this.daysList = daysList;
    }
}
