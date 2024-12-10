package com.example.skph.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "location_types")
public class LocationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean isPolygon;

    public LocationType(String typeName, String description, boolean isPolygon) {
        this.typeName = typeName;
        this.description = description;
        this.isPolygon = isPolygon;
    }

    public void updateDetails(String newName, String newDescription) {
        this.typeName = newName;
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return "LocationType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                ", isPolygon=" + isPolygon +
                '}';
    }
}