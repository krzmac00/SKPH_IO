package com.example.skph.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
public class Volunteer extends User {
    private String skills;

    @OneToMany
    private Collection<Task> tasks;

    @ManyToOne
    private Organisation organisation;


    public Volunteer() {
    }

    public Volunteer(String skills, Organisation organisation) {
        super();
        this.skills = skills;
        this.organisation = organisation;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

}
