package com.example.skph.controller;

import com.example.skph.model.Resource;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/resources")
public class ResourceViewController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceViewController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public String listAllResources(Model model) {
        model.addAttribute("resources", resourceService.getAllResources());
        return "resourceList"; // thymeleaf template
    }

    @GetMapping("/available")
    public String listAvailableResources(Model model) {
        model.addAttribute("resources", resourceService.findByStatus(ResourceStatus.AVAILABLE));
        return "resourceList";
    }

    @GetMapping("/assigned")
    public String listAssignedResources(Model model) {
        List<Resource> assigned = resourceService.getAllResources().stream()
                .filter(r -> r.getStatus() == ResourceStatus.IN_USE || r.getStatus() == ResourceStatus.ALLOCATED)
                .toList();
        model.addAttribute("resources", assigned);
        return "resourceList";
    }
}
