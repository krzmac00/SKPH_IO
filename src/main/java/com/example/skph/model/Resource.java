package com.example.skph.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="resource")
public abstract class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    public int amount;
}
