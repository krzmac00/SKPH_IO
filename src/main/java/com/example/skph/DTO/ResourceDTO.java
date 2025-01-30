package com.example.skph.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private Long id;
    private String name;
    private int amount;
    private boolean toGive;

    // Specyficzne pola dla różnych typów zasobów
    private String temperature;   // dla Food
    private Boolean allergyFree;  // dla Food
    private String size;          // dla Clothes
    private String sex;           // dla Clothes
    private Boolean withAnimals;  // dla Shelter
    private String description;   // dla Other
}

