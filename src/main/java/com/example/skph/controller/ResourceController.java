package com.example.skph.controller;

import com.example.skph.model.Resource;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.users.AidOrganization;
import com.example.skph.service.ResourceService;
import com.example.skph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;
    private final UserService userService;

    @Autowired
    public ResourceController(ResourceService resourceService,
                              UserService userService) {
        this.resourceService = resourceService;
        this.userService = userService;
    }

    /**
     * Zwraca listę zasobów (filtrowaną wg roli użytkownika, jeśli userId != null).
     */
    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

            switch (user.getRole()) {
                case AUTHORITY:
                    // Przedstawiciel władz widzi wszystkie zasoby
                    return ResponseEntity.ok(resourceService.getAllResources());

                case AID_ORGANIZATION:
                    // Organizacja widzi tylko zasoby przypisane do niej
                    AidOrganization orgUser = (AidOrganization) user;
                    List<Resource> assignedToOrg = resourceService.getResourcesAssignedToOrganization(orgUser.getId());
                    return ResponseEntity.ok(assignedToOrg);

                default:
                    // Inne role – brak dostępu
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        // Bez userId zwraca wszystkie zasoby
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    /**
     * Zwraca zasoby o konkretnym statusie (AVAILABLE, IN_USE, ALLOCATED, UNAVAILABLE).
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Resource>> getResourcesByStatus(@PathVariable("status") ResourceStatus status) {
        return ResponseEntity.ok(resourceService.findByStatus(status));
    }

    /**
     * Zwraca zasoby o statusie AVAILABLE.
     */
    @GetMapping("/available")
    public ResponseEntity<List<Resource>> getAvailableResources() {
        return ResponseEntity.ok(resourceService.findByStatus(ResourceStatus.AVAILABLE));
    }

    /**
     * Zwraca zasoby o statusie IN_USE lub ALLOCATED.
     */
    @GetMapping("/assigned")
    public ResponseEntity<List<Resource>> getAssignedResources() {
        List<Resource> assigned = resourceService.getAllResources().stream()
                .filter(r -> r.getStatus() == ResourceStatus.IN_USE || r.getStatus() == ResourceStatus.ALLOCATED)
                .toList();
        return ResponseEntity.ok(assigned);
    }

    /**
     * Znajdź zasób po ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        return resourceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Tworzy nowy zasób (np. PhysicalResource, FinancialResource, itp.).
     */
    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource created = resourceService.addResource(resource);
        return ResponseEntity.ok(created);
    }

    /**
     * Przydziela zasób do zadania (relacja 1:1).
     */
    @PostMapping("/{resourceId}/assign/{taskId}")
    public ResponseEntity<Resource> assignResourceToTask(@PathVariable Long resourceId, @PathVariable Long taskId) {
        Resource updated = resourceService.assignResourceToTask(resourceId, taskId);
        return ResponseEntity.ok(updated);
    }
}
