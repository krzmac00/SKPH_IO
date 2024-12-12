package com.example.skph.model.resources;

// Reprezentuje inne zasoby w systemie.
// Dziedziczy właściwości i metody z klasy bazowej Resource.
import com.example.skph.model.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.example.skph.model.enums.ResourceStatus;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "other_resources")
public class OtherResource extends Resource {

    // Opis zasobu.
    private String description;

    // Sprawdza, czy zasób jest dostępny.
    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE;
    }
}
