package com.example.skph.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Donor extends User {
    private String bankAccountNumber;

    public Donor() {
    }

    public Donor(String bankAccountNumber) {
        super();
        this.bankAccountNumber = bankAccountNumber;
    }
}
