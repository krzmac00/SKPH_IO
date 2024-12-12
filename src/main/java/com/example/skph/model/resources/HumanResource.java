package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "human_resources")
public class HumanResource extends Resource {

    private String role;

    private boolean availability;
}
