package com.example.skph.model;

import lombok.Getter;
import lombok.Setter;

public class Food extends Resource {
    @Getter
    @Setter
    private String temperature;

    @Getter
    @Setter
    private boolean allergyFree;

    public Food() {
    }
}
