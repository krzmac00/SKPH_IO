package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import com.example.skph.model.enums.TransportType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transport_resources")
public class TransportResource extends Resource {

    private int capacity;

    @Enumerated(EnumType.STRING)
    private TransportType type;
}
