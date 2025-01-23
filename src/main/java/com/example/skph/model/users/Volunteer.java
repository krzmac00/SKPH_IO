package com.example.skph.model.users;

// Reprezentuje ochotnika w systemie.
// Dziedziczy właściwości i metody z klasy bazowej User.
import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "volunteer")
public class Volunteer extends User {

    @OneToMany(mappedBy = "volunteer")
    private List<Task> tasks = new ArrayList<>(); // Zadania przypisane do ochotnika.

    @Setter
    @Getter
    @ManyToOne
    private AidOrganization assignedOrganization; // Organizacja, do której ochotnik został przypisany.

    private String skills;

    // Określa dostępność ochotnika.
    @Getter
    private boolean availability;

    public Volunteer(String skills, Organization organization) {
        super();
        this.skills = skills;
    }

    // Przypisuje ochotnika do zadania.
    public void assignTask(Task task) {
        if (this.isAvailable()) {
            tasks.add(task);
        } else {
            throw new IllegalStateException("Volunteer is not available.");
        }
    }

    // Sprawdza, czy ochotnik jest dostępny.
    public boolean isAvailable() {
        // dodać logikę sprawdzania dostępności
        return false;
    }

}