package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
//
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("OTHER")
public class OtherResource extends Resource {
    private String description; // Opis zasobu
    private String type; // Typ zasobu (np. niestandardowy typ)
}
