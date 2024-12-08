package com.example.skph.controller;

import com.example.skph.model.Requester;
import com.example.skph.service.RequesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/requesters")
public class RequesterController {

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
    @GetMapping("/lastName/{lastName}")
    public List<Requester> getRequestersByLastName(@PathVariable String lastName) {
        return requesterService.getRequesterByLastName(lastName);
    }

    // Pobierz Requester po imieniu i nazwisku
    @GetMapping("/fullName")
    public List<Requester> getRequestersByFirstAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return requesterService.getRequesterByFirstAndLastName(firstName, lastName);
    }
}
