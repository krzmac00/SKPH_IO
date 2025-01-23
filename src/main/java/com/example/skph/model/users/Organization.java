package com.example.skph.model.users;

// Reprezentuje organizację w systemie.
import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "organization")
@DiscriminatorValue("organization")
public class Organization extends User {
    @Getter
    @OneToMany(mappedBy = "assignedOrganization")
    private List<Resource> resources = new ArrayList<>();

    @OneToOne(mappedBy = "organization")
    private User user;

    @Setter
    @Getter
    private String name; // Nazwa organizacji.

    private String type; // Typ organizacji (np. NGO, fundacja, rządowa).

    private String contactInfo; // Dane kontaktowe organizacji.


}
