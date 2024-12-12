package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "human_resource")
public class HumanResource extends Resource {

    private String role;

    private boolean availability;
}
