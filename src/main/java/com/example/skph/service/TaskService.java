package com.example.skph.service;

import com.example.skph.enums.Status;
import com.example.skph.model.Task;
import com.example.skph.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLEngineResult;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + id));
    }

    // Usunięcie Task po ID
    @Transactional
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

    // Wyszukanie Task po Request ID
    public List<Task> getTasksByRequestId(Long requestId) {
        return taskRepository.findByRequestId(requestId);
    }

    // Wyszukanie ukończonych Task
//    public List<Task> getAccomplishedTasks() {
//        return taskRepository.findAccomplishedTasks();
//    }

    // Aktualizacja stanu ukończenia Task
    public Task updateTaskAccomplished(Long taskId, boolean accomplished) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(Status.closed);
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Task o podanym ID nie istnieje: " + taskId);
        }
    }
}
