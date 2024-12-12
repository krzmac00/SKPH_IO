package com.example.skph.model.resources;

// Reprezentuje zasoby finansowe, które są częścią systemu zarządzania zasobami.
// Dziedziczy właściwości i metody z klasy bazowej Resource.
import com.example.skph.model.Resource;
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
    private BigDecimal amount;

    // Waluta środków finansowych.
    private String currency;

    // Sprawdza, czy zasoby finansowe są dostępne.
    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    // Alokuje określoną kwotę z dostępnych środków.
    public void allocate(BigDecimal value) {
        if (isAvailable() && amount.compareTo(value) >= 0) {
            amount = amount.subtract(value);
            if (amount.compareTo(BigDecimal.ZERO) == 0) {
                setStatus(ResourceStatus.UNAVAILABLE);
            }
        } else {
            throw new IllegalArgumentException("Insufficient funds or resource unavailable.");
        }
    }
}
