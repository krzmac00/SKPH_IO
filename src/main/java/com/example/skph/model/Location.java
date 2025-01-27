package com.example.skph.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Convert(converter = LocationTypeConverter.class)
    @JsonDeserialize(using = LocationTypeDeserializer.class)
    private LocationType locationType;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Polygon, 4326)", nullable = true)
    private Polygon disasterArea;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Point, 4326)", nullable = true)
    private Point coordinates;

    public Location(String name, LocationType locationType, Point coordinates) {
        this.name = name;
        this.locationType = locationType;
        this.coordinates = coordinates;
    }

    public Location(String name, LocationType locationType, Polygon disasterArea) {
        this.name = name;
        this.locationType = locationType;
        this.disasterArea = disasterArea;

    }

}