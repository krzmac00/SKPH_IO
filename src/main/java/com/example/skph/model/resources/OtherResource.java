package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("OTHER")
public class OtherResource extends Resource {
    private String description; // Opis zasobu
    private String type; // Typ zasobu (np. niestandardowy typ)
}
