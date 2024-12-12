package com.example.skph.model;

import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationID;

    private String type;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "id")
    private Donor donor;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    public Donation(String type, BigDecimal amount, Donor donor) {
        this.type = type;
        this.amount = amount;
        this.donor = donor;
    }
}