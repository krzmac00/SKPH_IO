package com.example.skph.controller;

import com.example.skph.model.Location;
import com.example.skph.service.MapService;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @GetMapping
    public ResponseEntity<Object> getLocations() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapService.getLocations());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locations not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLocation(@PathVariable("id") long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapService.getLocation(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }
    }

    @PostMapping
    public ResponseEntity<Object> addLocation(@RequestBody Location location) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapService.addLocation(location));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLocation(@PathVariable("id") long id) {
        try {
            mapService.deleteLocation(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location not found");
        }
    }


//    @GetMapping("/radius")
//    public List<Location> findLocationsWithinRadius(
//            @RequestParam double latitude,
//            @RequestParam double longitude,
//            @RequestParam double radius) {
//        return mapService.findLocationsWithinRadius(latitude, longitude, radius);
//    }
//
//    @PostMapping("/geometry")
//    public List<Location> findLocationsWithinGeometry(@RequestBody Geometry geometry) {
//        return mapService.findLocationsWithinPolygon(geometry);
//    }
//
//    @GetMapping("/name")
//    public List<Location> getLocationsByName(@RequestParam String name) {
//        return mapService.getLocationsByName(name);
//    }
}
