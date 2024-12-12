package com.example.skph.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
public class Volunteer extends User {
    private String skills;
    private Collection<Task> tasks;
    private Organisation organisation;


    public Volunteer() {
    }

    public Volunteer(String skills, Organisation organisation) {
        super();
        this.skills = skills;
        this.organisation = organisation;
    }

}
