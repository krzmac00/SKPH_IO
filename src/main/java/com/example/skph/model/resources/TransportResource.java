package com.example.skph.model.resources;

import com.example.skph.model.Resource;
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
@Table(name = "transport_resources")
public class TransportResource extends Resource {

    private int capacity;

    @Enumerated(EnumType.STRING)
    private TransportType type;

    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE;
    }

    public void allocate() {
        if (isAvailable()) {
            setStatus(ResourceStatus.IN_USE);
        } else {
            throw new IllegalStateException("Transport resource is not available.");
        }
    }
}
