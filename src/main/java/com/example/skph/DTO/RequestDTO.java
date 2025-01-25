package com.example.skph.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class RequestDTO {
    private long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String address;
    private List<TaskDTO> tasks;

    public RequestDTO(long id, LocalDateTime startDate, LocalDateTime endDate, String address, List<TaskDTO> tasks) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = address;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getAddress() {
        return address;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }
}
