package com.example.skph.model.users;

import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "volunteer")
public class Volunteer extends User {

    private String skills;
    private Collection<Task> tasks;
    private boolean available;


    public Volunteer(String skills, Organization organization) {
        super();
        this.skills = skills;
    }

}
