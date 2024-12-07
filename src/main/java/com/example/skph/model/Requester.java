package com.example.skph.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name="requester")
@Getter
@Setter
public class Requester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NonNull
    private String lastName;

    public Requester() {
    }

    public Requester(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
