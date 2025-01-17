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

    @Enumerated(EnumType.ORDINAL)
//    @Column(name = "location_type")
    @JsonDeserialize(using = LocationTypeDeserializer.class)
    private LocationType locationType;

//    @Column(nullable = false)
//    private Double latitude;

//   @Column(nullable = false)
//    private Double longitude;
    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Point, 4326)", nullable = true)
    private Point coordinates;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Polygon, 4326)", nullable = true)
    private Polygon disasterArea;

    public Location(String name, LocationType locationType, Point coordinates) {
        this.name = name;
        this.locationType = locationType;
        this.coordinates = coordinates;
        //this.latitude = latitude;
        //this.longitude = longitude;
    }

    public Location(String name, LocationType locationType, Polygon disasterArea) {
        this.name = name;
        this.locationType = locationType;
        this.disasterArea = disasterArea;
        //this.latitude = latitude;
        //this.longitude = longitude;
    }


//    @JdbcTypeCode(SqlTypes.GEOMETRY)
//    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
//    private Point coordinates;
//
//    public Location(String name, LocationType locationType, Point coordinates) {
//        this.name = name;
//        this.locationType = locationType;
//        this.coordinates = coordinates;
//    }
//
//    public double calculateDistance(Location other) {
//        return this.coordinates.distance(other.getCoordinates());
//    }

}