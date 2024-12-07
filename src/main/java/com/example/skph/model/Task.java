package com.example.skph.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter
    @Setter
    private Resource resource;

    @NotNull
    @Getter
    @Setter
    private boolean accomplished;

    @NotNull
    @Getter
    private ArrayList<String> daysList = new ArrayList<>();
    //status: undone, pending, partiallyCompleted, completed, rejected, failed

    public Task(Resource resource, ArrayList<String> daysList) {
        this.resource = resource;
        this.accomplished = false;
        this.daysList = daysList;
    }
}
