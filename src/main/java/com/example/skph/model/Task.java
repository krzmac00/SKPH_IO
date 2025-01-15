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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "resource")
    private Resource resource;

    @NotNull
    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "days_list", joinColumns = @JoinColumn(name = "task_id"))
    private List<Day> daysList;
    //status: created, pending, inProgress, completed, closed, canceled

    @ManyToOne
    @JoinColumn(name = "request_id") //resource department use it to access address of task;
    private Request request;

    @Getter
    @Setter
    int grade; //type will be assigned by volunteer module

    @Getter
    @Setter
    String volunteer; //type will be assigned by volunteer module

    public Task() {
    }

    public Task(Resource resource/*, List<Day> daysList*/) {
        this.resource = resource;
        List<Day> statuses = new ArrayList<>();
        Day day = new Day(Status.fromValue(1), 0);
        statuses.add(day);
        this.daysList = statuses;
//        int dayAmount = rr.getResource().getAmount();
//        for (int i = 0; i <= dayAmount; i++) {
//            //change instead of last day giving final status, it will have 1 additional "day"
//            //that will represent that
//            statuses.add(new Day(Status.fromValue(1), i));
//        }
//        this.daysList = statuses;
    }

    public void setStatus(Status status) { //or a number i dont know
        int newDay = daysList.getLast().getDayIndex() + 1;
        Day day = new Day(status, newDay);
        this.daysList.add(day);
    }
}
