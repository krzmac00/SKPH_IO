package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "other")
public class Other extends Resource {
    @Getter
    @Setter
    private String description;

    public Other() {
    }

    public Other(String name, int amount, String description) {

        super();
        this.name = name;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
