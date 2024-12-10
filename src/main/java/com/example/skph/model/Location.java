package com.example.skph.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

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

    @ManyToOne
    @JoinColumn(name = "location_type_id", nullable = false)
    private LocationType locationType;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
    private Point coordinates;

    public Location(String name, LocationType locationType, Point coordinates) {
        this.name = name;
        this.locationType = locationType;
        this.coordinates = coordinates;
    }

    public double calculateDistance(Location other) {
        return this.coordinates.distance(other.getCoordinates());
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locationType=" + locationType.getTypeName() +
                ", coordinates=" + coordinates +
                '}';
    }
}