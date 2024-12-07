package com.example.skph.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@jakarta.persistence.Entity
@Table(name="address")
public class Address {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String address;

    @NonNull
    @Getter
    @Setter
    private String coordinates;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    /*public Address(String coordinates) {
        this.coordinates = coordinates;
    }*/


    public Address(String address, String coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }
}
