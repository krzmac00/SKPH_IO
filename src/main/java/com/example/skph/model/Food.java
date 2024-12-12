package com.example.skph.model;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "food")
public class Food extends Resource {
    @Getter
    @Setter
    private String temperature;

    @Getter
    @Setter
    private boolean allergyFree;

    public Food() {
    }

    public Food(String temperature, boolean allergyFree) {
        this.temperature = temperature;
        this.allergyFree = allergyFree;
    }
}
