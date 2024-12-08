package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Authority extends User {
    @OneToMany
    @JoinColumn(name = "authority_id") // Powiązanie zasobów dodawanych przez Authority
    private List<Resource> resources;

    public void addPublicResource(Resource resource) {
        // Logika dodania zasobu publicznego
    }

    public void allocateResource(Resource resource) {
        // Logika alokacji zasobu
    }
}
