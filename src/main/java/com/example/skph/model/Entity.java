package com.example.skph.model;

// Reprezentuje ogólny byt w systemie.
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name="entity")
@Getter
@Setter
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identyfikator bytu.

    @NonNull
    @NotNull
    private String name; // Nazwa bytu.

    public Entity() {
        this.name = "Null"; // Domyślna nazwa w przypadku braku wartości.
    }
}
