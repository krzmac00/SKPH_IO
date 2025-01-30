package com.example.skph.model.resources.physical;

import com.example.skph.model.Resource;
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

    public Clothes() {
        super(); // Konstruktor klasy bazowej (Resource) wywołany domyślnie
    }

    public Clothes(String name, int amount, String size, String sex) {
        super();
        this.name = name;
        this.amount = amount;
        this.size = size;
        this.sex = sex;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
