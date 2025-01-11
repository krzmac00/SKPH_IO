package com.example.skph.service;

import com.example.skph.model.Task;
import com.example.skph.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Zapisanie nowego Task
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Pobranie wszystkich Task
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Pobranie Task po ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Usunięcie Task po ID
    public void deleteTaskById(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Task o podanym ID nie istnieje: " + id);
        }
    }

    // Wyszukanie Task po Resource ID
    public List<Task> getTasksByResourceId(Long resourceId) {
        return taskRepository.findByResourceId(resourceId);
    }

    // Wyszukanie ukończonych Task
//    public List<Task> getAccomplishedTasks() {
//        return taskRepository.findAccomplishedTasks();
//    }

//    // Wyszukanie Task po liście dni
//    public List<Task> getTasksByDaysList(List<String> daysList) {
//        return taskRepository.findByDaysList(daysList);
//    }

    // Wyszukanie Task po Resource ID i stanie ukończenia
//    public List<Task> getTasksByResourceAndAccomplished(Long resourceId, boolean accomplished) {
//        return taskRepository.findByResourceAndAccomplished(resourceId, accomplished);
//    }

    // Aktualizacja stanu ukończenia Task
//    public Task updateTaskAccomplished(Long taskId/*, boolean accomplished*/) {
//        Optional<Task> optionalTask = taskRepository.findById(taskId);
//        if (optionalTask.isPresent()) {
//            Task task = optionalTask.get();
//            //task.setAccomplished(accomplished);
//            return taskRepository.save(task);
//        } else {
//            throw new IllegalArgumentException("Task o podanym ID nie istnieje: " + taskId);
//        }
//    }
}
