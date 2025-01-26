package com.example.skph.controller;

import com.example.skph.controller.dto.AssignResourceRequest;
import com.example.skph.controller.dto.TransportAssignmentRequest;
import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.resources.TransportResource;
import com.example.skph.service.ResourceService;
import com.example.skph.service.TaskService;
import com.example.skph.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ResourceService resourceService;
    private final TransportService transportService; // Dodany do obsługi transportu

    @Autowired
    public TaskController(TaskService taskService,
                          ResourceService resourceService,
                          TransportService transportService) {
        this.taskService = taskService;
        this.resourceService = resourceService;
        this.transportService = transportService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.getAllTasks(id);
        // Możesz dodać tu aktualizację innych pól, np. nazwy, location, itp.
        taskService.saveTask(updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Przykład przydzielenia zasobu do zadania z poziomu zadania
     * (drugi wariant - w drugą stronę).
     */
    @PostMapping("/{taskId}/assignResource")
    public ResponseEntity<Task> assignResourceToTask(
            @PathVariable Long taskId,
            @RequestBody AssignResourceRequest dto
    ) {
        Task task = taskService.getAllTasks(taskId);

        Long resourceId = dto.getResourceId();
        Resource resource = resourceService.findById(resourceId)
                .orElseThrow(() -> new NoSuchElementException("Resource not found with ID: " + resourceId));

        // Przypisujemy
        task.assignResource(resource);
        resourceService.update(resource); // Zapis do bazy
        taskService.saveTask(task);

        return ResponseEntity.ok(task);
    }

    /**
     * Przykład przypisania środka transportu do zadania
     */
    @PostMapping("/{taskId}/assignTransport")
    public ResponseEntity<Task> assignTransport(@PathVariable Long taskId,
                                                @RequestBody TransportAssignmentRequest dto) {
        Task task = taskService.getAllTasks(taskId);
        TransportResource transport = transportService.findById(dto.getTransportId())
                .orElseThrow(() -> new NoSuchElementException("Transport not found with ID: " + dto.getTransportId()));

        if (!transport.isAvailable()) {
            throw new IllegalStateException("Transport not available");
        }

        task.assignResource(transport);

        taskService.saveTask(task);

        return ResponseEntity.ok(task);
    }
}
