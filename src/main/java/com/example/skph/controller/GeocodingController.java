package com.example.skph.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GeocodingController {

    @GetMapping("/geocode")
    public String geocode(@RequestParam String q) {
        String url = "https://nominatim.openstreetmap.org/search?q=" + q + "&format=json";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        return response;
    }
}
