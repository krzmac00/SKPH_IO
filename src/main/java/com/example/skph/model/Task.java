package com.example.skph.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;
    private String review;

    @ManyToOne
    @JoinColumn(name = "id")
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "id")
    private Volunteer volunteer;

    public Task() {}

    public Task(String status, String review, Organisation organisation, Volunteer volunteer) {
        this.status = status;
        this.review = review;
        this.organisation = organisation;
        this.volunteer = volunteer;
    }
}
