package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import com.example.skph.model.enums.PhysicalResourceType;
import com.example.skph.model.enums.TransportType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.skph.model.enums.ResourceStatus;


import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "physical_resources")
public class PhysicalResource extends Resource {

    @Enumerated(EnumType.STRING)
    private PhysicalResourceType type;

    private int quantity;

    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE && quantity > 0;
    }

    public void allocate(int amount) {
        if (isAvailable() && amount <= quantity) {
            quantity -= amount;
            if (quantity == 0) {
                setStatus(ResourceStatus.UNAVAILABLE);
            }
        } else {
            throw new IllegalArgumentException("Insufficient quantity or resource unavailable.");
        }
    }
}