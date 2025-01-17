package com.example.skph.controller;

import com.example.skph.model.Location;
import com.example.skph.model.LocationType;
import com.example.skph.service.MapService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
@Profile("dev")
@RequestMapping("/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @GetMapping
    public String showMap() {
        return "map";
    }

    @GetMapping("/locations")
    public ResponseEntity<Object> getLocations() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapService.getLocations());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locations not found");
        }
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Object> getLocation(@PathVariable("id") long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapService.getLocation(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }
    }

    @PostMapping("/locations/point")
    public ResponseEntity<Object> addPointLocation(@RequestParam String name,
                                                   @RequestParam int locationType,
                                                   @RequestParam double latitude,
                                                   @RequestParam double longitude) {
        try {
            Location location = mapService.addPointLocation(name, LocationType.fromValue(locationType), latitude, longitude);
            return ResponseEntity.status(HttpStatus.CREATED).body(location);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating point location: " + e.getMessage());
        }
    }

    @PostMapping("/locations/polygon")
    public ResponseEntity<Object> addPolygonLocation(@RequestParam String name,
                                                     @RequestParam int locationType,
                                                     @RequestBody List<List<Double>> coordinates) {
        try {
            Coordinate[] polygonCoordinates = coordinates.stream()
                    .map(coord -> new Coordinate(coord.get(0), coord.get(1)))
                    .toArray(Coordinate[]::new);

            Location location = mapService.addPolygonLocation(name, LocationType.fromValue(locationType), polygonCoordinates);
            return ResponseEntity.status(HttpStatus.CREATED).body(location);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating polygon location: " + e.getMessage());
        }
    }

    @DeleteMapping("/locations/{id}")
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
