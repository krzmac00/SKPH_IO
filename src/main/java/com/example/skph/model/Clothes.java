package com.example.skph.model;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "clothes")
public class Clothes extends Resource {
    @Getter
    @Setter
    private String size;

    @Getter
    @Setter
    private String sex;
//
//    public Clothes() {
//    }
    public Clothes() {
        super(); // Konstruktor klasy bazowej (Resource) wywołany domyślnie
    }

    public Clothes(String name, int amount, String size, String sex) {
        super(); // Wywołanie konstruktora klasy bazowej
        this.name = name; // Ustawienie nazwy z klasy bazowej
        this.amount = amount; // Ustawienie ilości z klasy bazowej
        this.size = size;
        this.sex = sex;
    }
}
