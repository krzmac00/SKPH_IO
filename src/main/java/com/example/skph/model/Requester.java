package com.example.skph.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name="requester")
public class Requester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Getter
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String firstName;

    @NonNull
    @Getter
    @Setter
    private String lastName;

    public Requester() {
    }

    public Requester(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
