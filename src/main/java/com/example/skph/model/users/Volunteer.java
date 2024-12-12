package com.example.skph.model.users;

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

    @OneToOne
    private Task task;

    private boolean availability;

    @ManyToOne
    private AidOrganization assignedOrganization; // Add this field

    public boolean isAvailable() {
        return availability && task == null;
    }

    public void assignTask(Task task) {
        if (isAvailable()) {
            this.task = task;
            this.availability = false;
        } else {
            throw new IllegalStateException("Volunteer is not available.");
        }
    }

    // Add setter for assignedOrganization
    public void setAssignedOrganization(AidOrganization organization) {
        this.assignedOrganization = organization;
    }

    public AidOrganization getAssignedOrganization() {
        return assignedOrganization;
    }
}


