package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import com.example.skph.model.enums.PhysicalResourceType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "physical_resources")
public class PhysicalResource extends Resource {

    @Enumerated(EnumType.STRING)
    private PhysicalResourceType type;

    private int quantity;
}
