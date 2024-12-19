package com.example.skph.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String address;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

}