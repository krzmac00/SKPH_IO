package com.example.skph.model.maps;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    @ElementCollection
    private List<double[]> polygonCoordinates;

    @ManyToOne
    private LocationType type;

    public Location(double latitude, double longitude, LocationType type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public Location(List<double[]> polygonCoordinates, LocationType type) {
        this.polygonCoordinates = polygonCoordinates;
        this.type = type;
    }

    public String getCoordinates() {
        return String.format("%.6f, %.6f", latitude, longitude);
    }

    //Haversine Formula
    public double calculateDistance(Location other) {
        final int R = 6371; // promień Ziemi w kilometrach
        double latDistance = Math.toRadians(other.latitude - this.latitude);
        double lonDistance = Math.toRadians(other.longitude - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // odległość w kilometrach
    }

    public boolean isWithinRadius(Location center, double radius) {
        double distance = calculateDistance(center);
        return distance <= radius;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", type=" + type +
                '}';
    }
}
