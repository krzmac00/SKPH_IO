package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "donors")
public class Donor extends User {

    public void declareDonation(Resource resource) {
        // Implementacja deklaracji darowizny
    }

    public ResourceStatus trackResourceStatus(Resource resource) {
        // Implementacja śledzenia statusu zasobu
        return resource.getStatus();
    }
}
