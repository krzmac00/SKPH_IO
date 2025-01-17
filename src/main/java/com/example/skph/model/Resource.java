package com.example.skph.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@jakarta.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="resource")
public abstract class Resource {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    public String name;

    @OneToMany(mappedBy = "resource")
    Set<RequestResource> resourceList;

    @Getter
    @Setter
    public int amount;

    @Getter
    @Setter
    public boolean toGive; //to differentiate between what we have and what is needed
}
