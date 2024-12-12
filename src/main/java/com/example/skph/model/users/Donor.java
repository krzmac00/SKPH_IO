package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "donors")
public class Donor extends User {

    public void declareDonation(Resource resource) {
        resource.setStatus(ResourceStatus.ALLOCATED);
        resource.setAssignedOrganization(null); // Na razie bez przypisania
    }

    public ResourceStatus trackResourceStatus(Resource resource) {
        return resource.getStatus();
    }
}