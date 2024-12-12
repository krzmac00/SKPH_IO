package com.example.skph.model.resources;

// Reprezentuje zasoby fizyczne w systemie.
// Dziedziczy właściwości i metody z klasy bazowej Resource.
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

    // Typ zasobu fizycznego.
    @Enumerated(EnumType.STRING)
    private PhysicalResourceType type;

    // Ilość dostępnych zasobów fizycznych.
    private int quantity;

    // Sprawdza, czy zasób fizyczny jest dostępny.
    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE && quantity > 0;
    }

    // Alokuje określoną ilość zasobów fizycznych.
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
