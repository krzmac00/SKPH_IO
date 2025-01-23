package com.example.skph.dto;

import com.example.skph.model.maps.Location;
import com.example.skph.model.maps.LocationType;
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

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.locationType = LocationType.fromValue(location.getLocationType().getValue()-1);
        this.coordinates = location.getCoordinates() != null ? location.getCoordinates().toText() : null;
        this.disasterArea = location.getDisasterArea() != null ? location.getDisasterArea().toText() : null;
    }
}