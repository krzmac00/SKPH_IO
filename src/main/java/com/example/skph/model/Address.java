package com.example.skph.model;

import com.example.skph.model.victimRequest.Request;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "address")
    private List<Request> requests = new ArrayList<>();

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

}