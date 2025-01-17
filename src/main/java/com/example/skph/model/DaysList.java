/*package com.example.skph.model;

import com.example.skph.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="days_list")
public class DaysList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    List<Day> dayList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false) // "task_id" will be the foreign key column in DaysList table
    private Task task;

    public DaysList(List<Day> dayList, Task task) {
        this.dayList = dayList;
        this.task = task;
    }

    public DaysList() {}
}*/
