package com.example.skph.model.users;

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

    @OneToOne
    private Organization organization;

    @OneToMany(mappedBy = "assignedOrganization")
    private List<Resource> assignedResources;

    public void assignResourcesToTask(Task task, List<Resource> resources) {
        for (Resource resource : resources) {
            if (resource.isAvailable()) {
                resource.assignTask(task);
            } else {
                throw new IllegalStateException("Resource " + resource.getName() + " is not available.");
            }
        }
    }

    public void recruitVolunteer(Volunteer volunteer) {
        if (volunteer.isAvailable()) {
            volunteer.setAssignedOrganization(this);
        } else {
            throw new IllegalStateException("Volunteer is not available.");
        }
    }
}
