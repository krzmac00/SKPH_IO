package com.example.skph.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationID;

    private String type;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "id")
    private Donor donor;

    public Donation() {}
    public Donation(String type, int amount, Donor donor) {
        this.type = type;
        this.amount = amount;
        this.donor = donor;
    }

}
