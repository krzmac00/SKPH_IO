package com.example.skph.DTO;

import java.util.List;

public class TaskDTO {
    private long id;
    private String name;
    private List<DayDTO> days;

    public TaskDTO(long id, String name, List<DayDTO> days) {
        this.id = id;
        this.name = name;
        this.days = days;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<DayDTO> getDays() {
        return days;
    }
}
