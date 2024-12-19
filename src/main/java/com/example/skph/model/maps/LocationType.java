package com.example.skph.model.maps;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocationType {
    private String typeName;
    private String description;
    private boolean isPolygon;

    public LocationType(String typeName, String description, boolean isPolygon) {
        this.typeName = typeName;
        this.description = description;
        this.isPolygon = false;
    }

    public void updateDetails(String newName, String newDescription) {
        this.typeName = newName;
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return "LocationType{" +
                "typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
