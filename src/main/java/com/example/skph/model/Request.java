package com.example.skph.model;

import com.example.skph.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@jakarta.persistence.Entity
@Getter
@Table(name="request")
public class Request {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requester_id")
    @NotNull
    @Setter
    private Requester requester;

    @ManyToOne(cascade = CascadeType.ALL) //remember to reverse it
    @JoinColumn(name = "address_id")
    @NotNull
    @Setter
    private Address address;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> taskList;



    @Column
    @NotNull
    private boolean accomplished;

    @Setter
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<RequestResource> resourceList;


//    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<RequestResource> resourceList = new HashSet<>();


    @Column
    @NotNull
    private LocalDateTime startDate;

    @Column
    @Setter
    private LocalDateTime endDate;

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

//    public ArrayList<Task> generateTasks() {
//        if (resourceList == null || resourceList.isEmpty()) {
//            System.out.println("resourceList is null or empty");
//            return new ArrayList<>();
//        }
//
//        ArrayList<Task> tasks = new ArrayList<>();
//        for (RequestResource rr : resourceList) {
//            if (rr == null) {
//                System.out.println("Found null RequestResource in resourceList!");
//                continue;
//            }
//
//            if (rr.getResource() == null) {
//                System.out.println("RequestResource found, but getResource() is null!");
//                continue;
//            }
//
//            Task task = new Task(rr.getResource(), this);
//            tasks.add(task);
//        }
//
//        System.out.println("Generated " + tasks.size() + " tasks.");
//        this.taskList = tasks;
//        return tasks;
//    }


    public Request(Requester requester, Address address) {
        this.requester = requester;
        this.address = address;
        this.accomplished = false;
        this.resourceList = new HashSet<>();
        this.startDate = LocalDateTime.now();
        this.endDate = null;
    }


    @NotNull
    public boolean isAccomplished() {
        return accomplished;
    }

    public Request() {
        this.startDate = LocalDateTime.now();
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
}
