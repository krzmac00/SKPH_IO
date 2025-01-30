package com.example.skph.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requester_id")
    @NotNull
    @Getter
    @Setter
    private Requester requester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @NotNull
    @Getter
    @Setter
    private Address address;


    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    @Getter
    private List<Task> taskList = new ArrayList<>();

    @Column
    @NotNull
    @Getter
    private boolean accomplished;


    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> resourceList = new ArrayList<>();

    @Column
    @NotNull
    @Getter
    private LocalDate startDate;

    @Column
    @Getter
    private LocalDate endDate;

    public ArrayList<Task> generateTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (RequestResource rr : resourceList) {
            if (rr != null) {
                tasks.add(new Task(rr.getResource(), this/*, statuses*/));
            }
        }
        this.taskList = tasks;
        return tasks;
    }

    public Request(Requester requester, Address address,  ArrayList<Resource> resourceList, LocalDate endDate) {
        this.requester = requester;
        this.address = address;
        this.taskList = generateTasks();
        this.accomplished = false;
        this.resourceList = new ArrayList<>();
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public Request(Requester requester, Address address) {
        this.requester = requester;
        this.address = address;
        this.accomplished = false;
        this.resourceList = new HashSet<>();
        this.startDate = LocalDateTime.now();
        this.endDate = null;
    }

    public Request() {
        this.startDate = LocalDate.now();
    }

    @NotNull
    public boolean isAccomplished() {
        return accomplished;
    }

    public boolean accomplishedCheck() {
        boolean accomplished = true;

        for (Task task : this.getTaskList()) {
            Optional<Day> lastDay = task.getStatusHistory().stream()
                    .max(Comparator.comparingInt(Day::getDayIndex));

            if (lastDay.isPresent()) {
                Status lastDayStatus = lastDay.get().getStatus();
                //Check if the last day's status is CREATED, PENDING, IN_PROGRESS or COMPLETED
                if (lastDayStatus.equals(Status.fromValue(1)) || //CREATED
                        lastDayStatus.equals(Status.fromValue(2)) || //PENDING
                        lastDayStatus.equals(Status.fromValue(3)) || //IN_PROGRESS
                        lastDayStatus.equals(Status.fromValue(4))) { //COMPLETED
                    accomplished = false;
                }
            }
        }

        if (accomplished) { //set endDate when request is accomplished. Otherwise just leave it null
            setEndDate(LocalDateTime.now());
        }
        return accomplished;
    }

//    public boolean accomplishedCheck() {
//        boolean accomplished = true;
//        for (Task task : this.getTaskList()) {
//            String status = task.getDaysList().getLast();
//            if (status.equals("created") || status.equals("pending") || status.equals("inProgress")) {
//                accomplished = false;
//            }
//        }
//        return accomplished;
//    }
}
