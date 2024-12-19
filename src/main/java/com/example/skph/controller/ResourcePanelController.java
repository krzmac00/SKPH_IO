package com.example.skph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Sprawić że działa
@Controller
public class ResourcePanelController {

    @GetMapping
    public String showResourcePanel(Model model) {
        return "resourcePanel";
    }

    @PostMapping("/generate-report")
    public String generateReport(@RequestParam("fromDate") String fromDate,
                                 @RequestParam("toDate") String toDate,
                                 @RequestParam("reportType") String reportType,
                                 @RequestParam("organization") String organization,
                                 @RequestParam("resource") String resource) {
        return "report";
    }

}
