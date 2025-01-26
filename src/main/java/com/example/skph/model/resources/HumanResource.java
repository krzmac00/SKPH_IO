package com.example.skph.model.resources;

// Reprezentuje zasoby ludzkie w systemie.
// Dziedziczy właściwości i metody z klasy bazowej Resource.
import com.example.skph.model.Resource;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.skph.model.enums.ResourceStatus;

@Entity
@Data
@NoArgsConstructor
@Table(name = "human_resource")
public class HumanResource extends Resource {

    // Rola przypisana do zasobu ludzkiego.
    private String role;

    // Określa dostępność zasobu ludzkiego.
    private boolean availability;

    // Sprawdza, czy zasób ludzki jest dostępny.
    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE && availability;
    }

    // Przypisuje zasób ludzki do zadania.
    public void assignToTask() {
        if (isAvailable()) {
            availability = false;
            setStatus(ResourceStatus.IN_USE);
        } else {
            throw new IllegalStateException("Human resource is not available.");
        }
    }
}
