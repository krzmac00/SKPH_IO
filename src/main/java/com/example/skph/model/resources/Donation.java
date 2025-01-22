package com.example.skph.model.resources;

// Reprezentuje darowiznę w systemie.
import com.example.skph.model.users.Donor;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationID; // Identyfikator darowizny.

    private String type;

    private BigDecimal value; // Wartość darowizny.

    @ManyToOne
    @JoinColumn(name = "id")
    private Donor donor;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType; // Typ zasobu darowizny.

    @Enumerated(EnumType.STRING)
    private ResourceStatus status; // Status darowizny.

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource; // Powiązany zasób.

    public Donation(String type, BigDecimal amount, Donor donor) {
        this.type = type;
        this.amount = amount;
        this.donor = donor;
    }
}