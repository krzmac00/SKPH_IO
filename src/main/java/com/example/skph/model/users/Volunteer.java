package com.example.skph.model.users;

// Reprezentuje ochotnika w systemie.
// Dziedziczy właściwości i metody z klasy bazowej User.
import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "volunteers")
public class Volunteer extends User {

    // Zadanie przypisane do ochotnika.
    @OneToOne
    private Task task;

    // Określa dostępność ochotnika.
    private boolean availability;

    // Organizacja, do której ochotnik został przypisany.
    @ManyToOne
    private AidOrganization assignedOrganization;

    // Sprawdza, czy ochotnik jest dostępny.
    public boolean isAvailable() {
        return availability && task == null;
    }

    // Przypisuje ochotnika do zadania.
    public void assignTask(Task task) {
        if (isAvailable()) {
            this.task = task;
            this.availability = false;
        } else {
            throw new IllegalStateException("Volunteer is not available.");
        }
    }

    // Ustawia organizację, do której przypisano ochotnika.
    public void setAssignedOrganization(AidOrganization organization) {
        this.assignedOrganization = organization;
    }

    // Pobiera organizację przypisaną do ochotnika.
    public AidOrganization getAssignedOrganization() {
        return assignedOrganization;
    }
}