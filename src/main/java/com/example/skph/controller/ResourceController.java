package com.example.skph.controller;

import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.service.ResourceService;
import com.example.skph.service.TaskService; // Zakładam, że masz TaskService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;
    private final TaskService taskService; // Usługa do zarządzania zadaniami

    @Autowired
    public ResourceController(ResourceService resourceService, TaskService taskService) {
        this.resourceService = resourceService;
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource createdResource = resourceService.addResource(resource);
        return ResponseEntity.ok(createdResource);
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        return resourceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{resourceId}/assign/{taskId}")
    public ResponseEntity<Resource> assignResourceToTask(@PathVariable Long resourceId, @PathVariable Long taskId) {
        Resource updatedResource = resourceService.assignResourceToTask(resourceId, taskId);
        return ResponseEntity.ok(updatedResource);
    }

}
