package com.example.skph.controller;

import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.resources.TransportResource;
import com.example.skph.service.ResourceService;
import com.example.skph.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;
    private final ResourceService resourceService;

    @Autowired
    public TaskViewController(TaskService taskService,
                              ResourceService resourceService) {
        this.taskService = taskService;
        this.resourceService = resourceService;
    }

    @GetMapping
    public String listAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskList"; // np. taskList.html
    }

    @GetMapping("/{id}")
    public String showTaskDetails(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("No task with id=" + id));
        model.addAttribute("task", task);

        // Możemy dodać przy okazji listę zasobów, by je przypisać z poziomu zadania
        List<Resource> available = resourceService.findByStatus(ResourceStatus.AVAILABLE);
        model.addAttribute("availableResources", available);

        return "taskDetails"; // np. taskDetails.html
    }

    /**
     * (Przydzielanie zasobu z poziomu zadania)
     * np. w widoku taskDetails.html zrobisz formularz z parametrem resourceId
     */
    @PostMapping("/{taskId}/assignResource")
    public String assignResourceToTask(@PathVariable Long taskId,
                                       @RequestParam("resourceId") Long resourceId) {
        resourceService.assignResourceToTask(resourceId, taskId);
        return "redirect:/tasks/" + taskId;
    }

    /**
     * Przykład zlecenia transportu w 1 formularzu:
     * - Wybór transportu (TransportResource),
     * - Wybór ładunku (np. listy zasobów fizycznych),
     * - Wybór / ustawienie trasy
     */
    @PostMapping("/{taskId}/assignTransport")
    public String assignTransport(@PathVariable Long taskId,
                                  @RequestParam Long transportId,
                                  @RequestParam List<Long> cargoIds,
                                  @RequestParam String route) {
        // 1. Znajdź i sprawdź dostępny transport
        Resource r = resourceService.findById(transportId).orElseThrow();
        if (!(r instanceof TransportResource transport)) {
            throw new IllegalArgumentException("Resource is not transport type!");
        }

        // 2. Przypisz transport do zadania
        resourceService.assignResourceToTask(transportId, taskId);

        // 3. Przypisz ładunek (np. lista ID zasobów) do zadania
        //    (Możesz iterować po cargoIds i wywoływać assignResourceToTask)

        // 4. Zapisz trasę w zadaniu (musisz mieć w encji Task pole route itp.)

        return "redirect:/tasks/" + taskId;
    }
}
