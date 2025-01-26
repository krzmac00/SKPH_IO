package com.example.skph.model.victimRequest;

import com.example.skph.model.Address;
import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.enums.TaskStatus;
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
//    @JoinTable(name = "requester")
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
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> resourceList = new ArrayList<>();

    @Column
    @NotNull
    @Getter
    private LocalDate startDate;

    @Column
    @Getter
    private LocalDate endDate;

    private List<Task> generateTasks() {
        List<Task> tasks = new ArrayList<>();
        for (Resource resource : resourceList) {
            if (resource != null) {
                List<String> status = new ArrayList<>();
                int resourceAmount = resource.getAmount();
                Task task = new Task();
                task.assignResource(resource);
                tasks.add(task);
            }
        }
        return tasks;
    }

    public Request(Requester requester, Address address,  ArrayList<Resource> resourceList, LocalDate endDate) {
        this.requester = requester;
        this.address = address;
//        this.taskList = generateTasks();
        this.taskList = generateTasks();
        this.accomplished = false;
//        this.resourceList = resourceList;
        this.resourceList = new ArrayList<>();
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public Request() {
        this.startDate = LocalDate.now();
    }

    public boolean accomplishedCheck() {
        boolean accomplished = true;
        for (Task task : this.getTaskList()) {
            TaskStatus status = task.getStatus();
            if (status == TaskStatus.CREATED || status == TaskStatus.PENDING || status == TaskStatus.IN_PROGRESS) {
                accomplished = false;
            }
        }
        return accomplished;
    }
}
