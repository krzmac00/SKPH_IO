package com.example.skph.model;

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

        super(); // Wywołanie konstruktora klasy bazowej
        this.name = name; // Ustawienie nazwy z klasy bazowej
        this.amount = amount; // Ustawienie ilości z klasy bazowej
        this.description = description;
    }
}
