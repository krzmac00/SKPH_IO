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
        super(); // Konstruktor klasy bazowej (Resource) wywołany domyślnie
    }

    // Konstruktor z parametrami dla klasy Food
    public Food(String name, int amount, String temperature, boolean allergyFree) {
        super();
        this.name = name;
        this.amount = amount;
        this.temperature = temperature;
        this.allergyFree = allergyFree;
    }
}
