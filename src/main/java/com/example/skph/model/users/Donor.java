package com.example.skph.model.users;

// Reprezentuje darczyńcę w systemie.
// Dziedziczy właściwości i metody z klasy bazowej User.
import com.example.skph.model.Resource;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.resources.Donation;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "donor")
public class Donor extends User {
    private String bankAccountNumber;

    @OneToMany(mappedBy = "donor")
    List<Donation> donations = new ArrayList<>();

    public Donor(String bankAccountNumber) {
        super();
        this.bankAccountNumber = bankAccountNumber;
    }

    // Deklaruje darowiznę w postaci zasobu.
    public void declareDonation(Resource resource) {
        resource.setStatus(ResourceStatus.ALLOCATED);
        resource.setAssignedOrganization(null); // Na razie bez przypisania
    }

    // Śledzi status przekazanego zasobu.
    public ResourceStatus trackResourceStatus(Resource resource) {
        return resource.getStatus();
    }
}
