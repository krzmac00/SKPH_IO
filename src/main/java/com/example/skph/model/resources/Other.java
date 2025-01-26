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

    public Other(String description) {
        this.description = description;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
