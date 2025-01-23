package com.example.skph.model.users;

// Reprezentuje organizacje pomocowe w systemie.
// Dziedziczy właściwości i metody z klasy bazowej User.
import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "aid_organization")
public class AidOrganization extends User {

    @OneToMany(mappedBy = "assignedOrganization")
    private List<Volunteer> volunteers = new ArrayList<>();


    // Przypisuje zasoby do konkretnego zadania.
    public void assignResourcesToTask(Task task, List<Resource> resources) {
        for (Resource resource : resources) {
            if (resource.isAvailable()) {
                resource.assignTask(task);
            } else {
                throw new IllegalStateException("Resource " + resource.getName() + " is not available.");
            }
        }
    }

    // Rekrutuje ochotnika do organizacji.
    public void recruitVolunteer(Volunteer volunteer) {
        if (volunteer.isAvailable()) {
            volunteer.setAssignedOrganization(this);
        } else {
            throw new IllegalStateException("Volunteer is not available.");
        }
    }

}
