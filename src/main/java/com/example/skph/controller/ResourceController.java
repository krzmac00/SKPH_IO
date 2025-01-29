package com.example.skph.controller;

import com.example.skph.model.Resource;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import com.example.skph.model.users.AidOrganization;
import com.example.skph.model.enums.UserRole;
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
     * Zwraca listę wszystkich zasobów w systemie (lub filtrowaną wg roli użytkownika).
     */
    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources(
            @RequestParam(required = false) Long userId
    ) {
        if (userId != null) {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));

            switch (user.getRole()) {
                case AUTHORITY:
                    return ResponseEntity.ok(resourceService.getAllResources());
                case AID_ORGANIZATION:
                    AidOrganization orgUser = (AidOrganization) user;
                    // np. getId() -> logika
                    List<Resource> assignedToOrg = resourceService.getResourcesAssignedToOrganization(orgUser.getId());
                    return ResponseEntity.ok(assignedToOrg);
//                case DONOR:
//                    // Darczyńca – np. getResourcesDonatedBy(donorId) → do zaimplementowania
//                    return ResponseEntity.ok(/*...*/);
                default:
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.ok(resourceService.getAllResources());
    }


    /**
     * Zwraca tylko zasoby o konkretnym statusie (np. AVAILABLE).
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Resource>> getResourcesByStatus(@PathVariable("status") ResourceStatus status) {
        return ResponseEntity.ok(resourceService.findByStatus(status));
    }

//    /**
//     * Zwraca tylko zasoby danego typu (PHYSICAL, HUMAN, etc.).
//     */
//    @GetMapping("/type/{resourceType}")
//    public ResponseEntity<List<Resource>> getResourcesByType(@PathVariable("resourceType") ResourceType resourceType) {
//        return ResponseEntity.ok(resourceService.findResourcesByType(resourceType));
//    }

//    /**
//     * Zwraca zasoby przypisane do organizacji o danym ID.
//     */
//    @GetMapping("/organization/{orgId}")
//    public ResponseEntity<List<Resource>> getResourcesByOrganization(@PathVariable("orgId") Long orgId) {
//        return ResponseEntity.ok(resourceService.getResourcesAssignedToOrganization(orgId));
//    }

    /**
     * Zwraca zasoby dostępne (AVAILABLE).
     */
    @GetMapping("/available")
    public ResponseEntity<List<Resource>> getAvailableResources() {
        return ResponseEntity.ok(resourceService.findByStatus(ResourceStatus.AVAILABLE));
    }

    /**
     * Zwraca zasoby przydzielone (IN_USE, ALLOCATED).
     */
    @GetMapping("/assigned")
    public ResponseEntity<List<Resource>> getAssignedResources() {
        List<Resource> all = resourceService.getAllResources();
        List<Resource> assigned = all.stream()
                .filter(r -> r.getStatus() == ResourceStatus.IN_USE || r.getStatus() == ResourceStatus.ALLOCATED)
                .toList();
        return ResponseEntity.ok(assigned);
    }

    // --------------------- CRUD / inne operacje -------------------------

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        return resourceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource createdResource = resourceService.addResource(resource);
        return ResponseEntity.ok(createdResource);
    }

    /**
     * Przypisanie zasobu do zadania.
     */
    @PostMapping("/{resourceId}/assign/{taskId}")
    public ResponseEntity<Resource> assignResourceToTask(@PathVariable Long resourceId, @PathVariable Long taskId) {
        Resource updatedResource = resourceService.assignResourceToTask(resourceId, taskId);
        return ResponseEntity.ok(updatedResource);
    }
}
