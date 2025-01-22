package com.example.skph.model.users;

// Reprezentuje organizacje pomocowe w systemie.
// Dziedziczy właściwości i metody z klasy bazowej User.
import com.example.skph.model.Resource;
import com.example.skph.model.Organization;
import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "aid_organizations")
public class AidOrganization extends User {

    // Organizacja, którą reprezentuje użytkownik.
    @OneToOne
    private Organization organization;

    // Lista zasobów przypisanych do organizacji.
    @OneToMany(mappedBy = "assignedOrganization")
    private List<Resource> assignedResources;

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

//    public void assignResourcesToTask(Task task, List<Resource> resources) {
//        resources.forEach(resource -> resource.assignTask(task));
//    }
}
