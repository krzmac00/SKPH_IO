package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "shelter")
public class Shelter extends Resource {

    @Getter
    @Setter
    private boolean withAnimals;

    public Shelter() {
    }

    public Shelter(boolean withAnimals) {
        this.withAnimals = withAnimals;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
