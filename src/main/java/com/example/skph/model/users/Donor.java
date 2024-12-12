package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "donor")
public class Donor extends User {
    private String bankAccountNumber;

    public Donor(String bankAccountNumber) {
        super();
        this.bankAccountNumber = bankAccountNumber;
    }

    public void declareDonation(Resource resource) {
        // Implementacja deklaracji darowizny
    }

    public ResourceStatus trackResourceStatus(Resource resource) {
        // Implementacja śledzenia statusu zasobu
        return resource.getStatus();
    }
}
