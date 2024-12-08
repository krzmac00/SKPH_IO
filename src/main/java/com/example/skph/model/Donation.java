package com.example.skph.model;

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
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationID;

    private Long donorID;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
}
