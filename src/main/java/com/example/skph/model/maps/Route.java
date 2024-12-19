package com.example.skph.model.maps;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Location start;
    private Location end;
    private double distance;
    private List<Location> waypoints;
    private double estimatedTime;

    public Route(Location start, Location end) {
        this.start = start;
        this.end = end;
        this.distance = calculateDistance();
        this.waypoints = new ArrayList<>();
    }

    private double calculateDistance() {
        double totalDistance = 0;
        Location previous = start;

        for (Location waypoint : waypoints) {
            totalDistance += previous.calculateDistance(waypoint);
            previous = waypoint;
        }

        totalDistance += previous.calculateDistance(end);
        return totalDistance;
    }

    public void addWaypoint(Location waypoint) {
        this.waypoints.add(waypoint);
        this.distance = calculateDistance();
    }

    public void removeWaypoint(Location waypoint) {
        this.waypoints.remove(waypoint);
        this.distance = calculateDistance();
    }

    public void estimateTime(double averageSpeed) {
        this.estimatedTime = distance / averageSpeed; // Czas = dystans / prędkość
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                ", waypoints=" + waypoints +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}