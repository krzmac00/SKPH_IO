package com.example.skph.model;

import com.example.skph.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="task")
public class Task {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "resource")
    private Resource resource;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Day> statusHistory;
    //private DaysList daysList;
    //status: created, pending, inProgress, completed, closed, canceled

    @Getter
    @ManyToOne
    @JoinColumn(name = "request_id") //resource department use it to access address of task;
    private Request request;

    /*@Getter
    @Setter
    int grade; //type will be assigned by volunteer module, needs to be inserted as a column of task table

    @Getter
    @Setter
    Volunteer volunteer; //type will be assigned by volunteer module, needs to be inserted as a column of task table*/
    //type Volunteer is not created in this module
    public Task() {
    }

    public Task(Resource resource, Request request) {
        this.resource = resource;
        this.request = request;
        //List<Day> statuses = new ArrayList<>();
        //Day day = new Day(Status.fromValue(1), 0);
        //statuses.add(day);
        //this.daysList = statuses;
    }

    public void setStatus(Status status) { //or a number i dont know
        int newDay = statusHistory.getLast().getDayIndex() + 1;
        Day day = new Day(status, newDay);
        this.statusHistory.add(day);
    }
}
