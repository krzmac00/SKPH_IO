package com.example.skph.model;

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

    public Shelter(String name, int amount, boolean withAnimals) {

        super(); // Wywołanie konstruktora klasy bazowej
        this.name = name; // Ustawienie nazwy z klasy bazowej
        this.amount = amount; // Ustawienie ilości z klasy bazowej
        this.withAnimals = withAnimals;
    }
}
