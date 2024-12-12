package com.example.skph.service;

import com.example.skph.model.Location;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenStreetMapService {
    private final String apiUrl = "https://nominatim.openstreetmap.org";

    private final RestTemplate restTemplate;

    public OpenStreetMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMapData(String coordinates) {
        return "Map data for coordinates: " + coordinates;
    }

    public Location geocodeAddress(String address) {
        String url = apiUrl + "/search?q=" + address + "&format=json";
        return null;
    }
}
