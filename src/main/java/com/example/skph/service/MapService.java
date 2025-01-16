package com.example.skph.service;

import com.example.skph.model.GeometryHelper;
import com.example.skph.model.Location;
//import com.example.skph.model.Route;
import com.example.skph.repository.LocationRepository;
//import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {

    @Autowired
    private LocationRepository locationRepository;

    public Location addLocation(Location location) {
        locationRepository.save(location);
        return location;
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

