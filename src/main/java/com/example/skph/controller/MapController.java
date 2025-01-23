package com.example.skph.controller;

import com.example.skph.dto.LocationDTO;
import com.example.skph.dto.LocationRequestDTO;
import com.example.skph.model.maps.Location;
import com.example.skph.model.maps.LocationType;
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
    ResponseEntity<List<LocationDTO>> getLocations() {
        List<LocationDTO> locations = mapService.getLocations().stream()
                .map(LocationDTO::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Object> getLocation(@PathVariable("id") long id) {
        try {
            LocationDTO locationDTO = mapService.getLocation(id);
            return ResponseEntity.status(HttpStatus.OK).body(locationDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }
    }

    @PostMapping("/locations/point")
    public ResponseEntity<Object> addPointLocation(@RequestBody LocationRequestDTO request) {
        try {
            Location location = mapService.addPointLocation(
                    request.getName(),
                    LocationType.fromValue(request.getLocationType()),
                    request.getLatitude(),
                    request.getLongitude()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(new LocationDTO(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating point location: " + e.getMessage());
        }
    }

    @PostMapping("/locations/polygon")
    public ResponseEntity<Object> addPolygonLocation(@RequestBody LocationRequestDTO request) {
        try {
            // Walidacja locationType
            if (request.getLocationType() < 1 || request.getLocationType() > 5) {
                throw new IllegalArgumentException("Invalid locationType: " + request.getLocationType());
            }

            Coordinate[] polygonCoordinates = request.getCoordinates().stream()
                    .map(coord -> new Coordinate(coord.get(0), coord.get(1)))
                    .toArray(Coordinate[]::new);

            Location location = mapService.addPolygonLocation(
                    request.getName(),
                    LocationType.fromValue(request.getLocationType()),
                    polygonCoordinates
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(new LocationDTO(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating polygon location: " + e.getMessage());
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
