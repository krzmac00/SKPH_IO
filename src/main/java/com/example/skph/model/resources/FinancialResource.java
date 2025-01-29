package com.example.skph.model.resources;

// Reprezentuje zasoby finansowe, które są częścią systemu zarządzania zasobami.
// Dziedziczy właściwości i metody z klasy bazowej Resource.
import com.example.skph.model.Resource;
import com.example.skph.model.enums.ResourceType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.skph.model.enums.ResourceStatus;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "financial_resources")
public class FinancialResource extends Resource {

    // Kwota dostępnych środków finansowych.
    private BigDecimal value;

    // Waluta środków finansowych.
    private String currency;

    // Sprawdza, czy zasoby finansowe są dostępne.
    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE && value.compareTo(BigDecimal.ZERO) > 0;
    }

    @PrePersist
    public void prePersist() {
        this.setResourceType(ResourceType.FINANCIAL);
    }

    // Alokuje określoną kwotę z dostępnych środków.
    public void allocate(BigDecimal value) {
        if (isAvailable() && this.value.compareTo(value) >= 0) {
            this.value = this.value.subtract(value);
            if (this.value.compareTo(BigDecimal.ZERO) == 0) {
                setStatus(ResourceStatus.UNAVAILABLE);
            }
        } else {
            throw new IllegalArgumentException("Insufficient funds or resource unavailable.");
        }
    }
}
