package com.example.skph.controller;

import com.example.skph.model.Request;
import com.example.skph.model.Requester;
import com.example.skph.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }

    @GetMapping("/byRequester")
    public List<Request> getRequestsByRequester(@RequestBody Requester requester) {
        return requestService.getRequestsByRequester(requester);
    }

    @GetMapping("/byStartDate")
    public List<Request> getRequestsByStartDate(@RequestParam String startDate) {
        LocalDate date = LocalDate.parse(startDate);
        return requestService.getRequestsByStartDate(date);
    }
}

