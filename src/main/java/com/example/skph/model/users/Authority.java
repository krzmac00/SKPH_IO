package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
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
