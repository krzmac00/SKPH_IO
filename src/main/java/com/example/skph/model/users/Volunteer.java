package com.example.skph.model.users;

import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "volunteers")
public class Volunteer extends User {

    @OneToOne
    private Task task;

    private boolean availability;
}
