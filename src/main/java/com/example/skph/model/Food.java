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

//    public Food() {
//    }
//
//    public Food(String temperature, boolean allergyFree) {
//        this.temperature = temperature;
//        this.allergyFree = allergyFree;
//    }

    public Food() {
        super(); // Konstruktor klasy bazowej (Resource) wywołany domyślnie
    }

    // Konstruktor z parametrami dla klasy Food
    public Food(String name, int amount, String temperature, boolean allergyFree) {
        super(); // Wywołanie konstruktora klasy bazowej
        this.name = name; // Ustawienie nazwy z klasy bazowej
        this.amount = amount; // Ustawienie ilości z klasy bazowej
        this.temperature = temperature;
        this.allergyFree = allergyFree;
    }
}
