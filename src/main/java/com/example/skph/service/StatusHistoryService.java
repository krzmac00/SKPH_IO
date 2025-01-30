package com.example.skph.service;

import com.example.skph.model.Day;
import com.example.skph.model.Task;
import com.example.skph.repository.StatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusHistoryService {
    private final StatusHistoryRepository statusHistoryRepository;

    @Autowired
    public StatusHistoryService(StatusHistoryRepository daysListRepository) {
        this.statusHistoryRepository = daysListRepository;
    }

    public Day getStatusHistoryById(Long id) {
        return statusHistoryRepository.findById(id).orElse(null);
    }

    public void saveStatusHistory(Day day) {
        statusHistoryRepository.save(day);
    }

    // Wyszukanie Day po id Taska
    public List<Day> getDaysByTaskId(Long taskId) {
        return statusHistoryRepository.findByTaskId(taskId);
    }
}
