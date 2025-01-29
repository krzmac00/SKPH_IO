package com.example.skph.model.resources;

// Reprezentuje zasoby transportowe w systemie.
// Dziedziczy właściwości i metody z klasy bazowej Resource.
import com.example.skph.model.Resource;
import com.example.skph.model.enums.ResourceType;
import com.example.skph.model.enums.TransportType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.skph.model.enums.ResourceStatus;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "transport_resource")
public class TransportResource extends Resource {

    // Pojemność zasobu transportowego.
    private int capacity;

    // Typ transportu.
    @Enumerated(EnumType.STRING)
    private TransportType type;

    // Sprawdza, czy zasób transportowy jest dostępny.
    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE;
    }

    @PrePersist
    public void prePersist() {
        this.setResourceType(ResourceType.TRANSPORT);
    }

    // Alokuje zasób transportowy do użytku.
    public void allocate() {
        if (isAvailable()) {
            setStatus(ResourceStatus.IN_USE);
        } else {
            throw new IllegalStateException("Transport resource is not available.");
        }
    }
}
