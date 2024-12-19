package com.example.skph.controller;

import com.example.skph.model.maps.Location;
import com.example.skph.model.maps.Route;
import com.example.skph.service.maps.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/map")
@CrossOrigin
public class MapController {
    @Autowired
    private MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return mapService.getAllLocations();
    }

    @PostMapping("/locations/point")
    public Location addPoint(@RequestBody Location location) {
        return mapService.addLocation(location);
    }

    @PostMapping("/locations/polygon")
    public Location addPolygon(@RequestBody Location location) {
        return mapService.addLocation(location);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable int id) {
        mapService.deleteLocation(id);
    }

    @GetMapping("/locations/by-type")
    public List<Location> getLocationsByType(@RequestParam String typeName) {
        return mapService.findLocationsByType(typeName);
    }

    @GetMapping("/locations/by-description")
    public List<Location> getLocationsByDescription(@RequestParam String description) {
        return mapService.findLocationsByDescription(description);
    }

    @GetMapping("/routes")
    public List<Route> getAllRoutes() {
        return mapService.getRoutes();
    }

    @PostMapping("/routes")
    public Route addRoute(@RequestBody Route route) {
        return mapService.addRoute(route);
    }

    @DeleteMapping("/routes/{id}")
    public void deleteRoute(@PathVariable int id) {
        mapService.deleteRoute(id);
    }
}
