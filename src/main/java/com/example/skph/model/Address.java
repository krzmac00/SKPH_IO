package com.example.skph.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class Address {

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String coordinates;
}
