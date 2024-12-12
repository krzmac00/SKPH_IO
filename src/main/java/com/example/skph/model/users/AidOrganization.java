package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "aid_organizations")
public class AidOrganization extends User {

    public void assignResourcesToTask(Task task, List<Resource> resources) {
        resources.forEach(resource -> resource.assignTask(task));
    }
}
