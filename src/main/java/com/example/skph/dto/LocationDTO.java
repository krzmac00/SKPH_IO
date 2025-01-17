package com.example.skph.dto;

import com.example.skph.model.LocationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private Long id;
    private String name;
    private LocationType locationType;
    private String coordinates;
    private String disasterArea;

    public LocationDTO(Long id, String name, LocationType locationType, String coordinates, String disasterArea) {
        this.id = id;
        this.name = name;
        this.locationType = locationType;
        this.coordinates = coordinates;
        this.disasterArea = disasterArea;
    }
}