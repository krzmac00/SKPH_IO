package com.example.skph.controller;

import com.example.skph.model.Requester;
import com.example.skph.service.RequesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final RequesterService requesterService;

    @Autowired
    public RequesterController(RequesterService requesterService) {
        this.requesterService = requesterService;
    }

    // Pobierz Requester po ID
    @GetMapping("/{id}")
    public Requester getRequesterById(@PathVariable Long id) {
        return requesterService.getRequesterById(id);
    }

    // Pobierz Requester po nazwisku
    @GetMapping("/address/{lastName}")
    public List<Requester> getRequestersByLastName(@PathVariable String lastName) {
        return requesterService.getRequesterByLastName(lastName);
    }

    // Pobierz Requester po imieniu i nazwisku
    @GetMapping("/coordinates/{coordinates}")
    public List<Requester> getRequestersByFirstAndLastName(
            @RequestParam String coordinates,
            @RequestParam String lastName) {
        return requesterService.getRequesterByFirstAndLastName(firstName, lastName);
    }

    @GetMapping("/isCoordinates/isCoordinates")
    public List<Requester> getRequestersByFirstAndLastName(
            @RequestParam boolean isCoordinates {
        return requesterService.getRequesterByFirstAndLastName(firstName, lastName);
    }
}*/
