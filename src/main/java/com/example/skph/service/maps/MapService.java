//package com.example.skph.service.maps;
//
//import com.example.skph.model.maps.Location;
//import com.example.skph.model.maps.Route;
//import com.example.skph.repository.LocationRepository;
//import com.example.skph.repository.RouteRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MapService {
//    //private final LocationRepository locationRepository;
//    private final RouteRepository routeRepository;
//
//    public MapService(/*LocationRepository locationRepository,*/ RouteRepository routeRepository) {
//        //this.locationRepository = locationRepository;
//        this.routeRepository = routeRepository;
//    }
//
////    public List<Location> getAllLocations() {
////        return (List<Location>) locationRepository.findAll();
////    }
//
////    public Location addLocation(Location location) {
////        return locationRepository.save(location);
////    }
////
////    public void deleteLocation(int id) {
////        locationRepository.deleteById(id);
////    }
////
////    public List<Location> findLocationsByType(String typeName) {
////        return locationRepository.findByType_TypeName(typeName);
////    }
////
////    public List<Location> findLocationsByDescription(String description) {
////        return locationRepository.findByDescriptionContainingIgnoreCase(description);
////    }
//
//    public List<Route> getRoutes() {
//        return (List<Route>) routeRepository.findAll();
//    }
//
//    public Route addRoute(Route route) {
//        return routeRepository.save(route);
//    }
//
//    public void deleteRoute(int id) {
//        routeRepository.deleteById(id);
//    }
//}
