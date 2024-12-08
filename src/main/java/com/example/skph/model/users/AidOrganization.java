package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.Organization;
import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
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

    public void assignResourcesToTask(Task task, List<Resource> resources) {
        resources.forEach(resource -> resource.assignTask(task));
    }
}
