package com.example.skph.controller;

import com.example.skph.model.Task;
import com.example.skph.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Wyświetlenie listy zadań
    @GetMapping
    public String listTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskList"; // plik task-list.html
    }

    // Formularz dodawania nowego zadania
    @GetMapping("/add")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "taskAdd"; // plik task-add.html
    }

    // Obsługa zapisu nowego zadania
    @PostMapping("/add")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks"; // Przekierowanie na listę zadań
    }

    // Formularz edycji zadania
    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "taskEdit"; // plik task-edit.html
        } else {
            return "redirect:/tasks";
        }
    }

    // Obsługa zapisu zmodyfikowanego zadania
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task updatedTask) {
        Optional<Task> existingTask = taskService.getTaskById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setResource(updatedTask.getResource());
            taskService.saveTask(task);
        }
        return "redirect:/tasks";
    }

    // Obsługa usuwania zadania
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }
}

