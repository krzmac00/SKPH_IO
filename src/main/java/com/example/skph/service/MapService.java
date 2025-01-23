package com.example.skph.service;

import com.example.skph.dto.LocationDTO;
import com.example.skph.model.GeometryHelper;
import com.example.skph.model.maps.Location;
//import com.example.skph.model.Route;
import com.example.skph.model.maps.LocationType;
import com.example.skph.repository.LocationRepository;
//import org.locationtech.jts.geom.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Autowired
    private LocationRepository locationRepository;

    public LocationDTO getLocation(long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return new LocationDTO(location);
    }

    public Location addPointLocation(String name, LocationType locationType, double latitude, double longitude) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point coordinates = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        Location location = new Location(name, locationType, coordinates);
        return locationRepository.save(location);
    }

    public Location addPolygonLocation(String name, LocationType locationType, Coordinate[] coordinates) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Polygon polygon = geometryFactory.createPolygon(coordinates);

        Location location = new Location(name, locationType, polygon);
        return locationRepository.save(location);
    }


    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public Location getLocation(Long id) {
        return locationRepository.findById(id).get();
    }

    public Location deleteLocation(Long id) {
        locationRepository.delete(locationRepository.findById(id).get());
        return locationRepository.findById(id).get();
    }

//    public List<Location> findLocationsWithinRadius(double latitude, double longitude, double radius) {
//        Point point = GeometryHelper.createPoint(latitude, longitude);
//        return locationRepository.findLocationsWithinRadius(point, radius);
//    }
//
//    public List<Location> findLocationsWithinPolygon(Geometry geometry) {
//        return locationRepository.findLocationsWithinGeometry(geometry);
//    }
//
//    public List<Location> getLocationsByName(String name) {
//        return locationRepository.findByName(name);
//    }
}

