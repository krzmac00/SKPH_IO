package com.example.skph.model;

import com.example.skph.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@jakarta.persistence.Entity
@Table(name="request")
public class Request {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Getter
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "requester")
    @JoinColumn(name = "requester_id")
    @NotNull
    @Getter
    @Setter
    private Requester requester;

    @ManyToOne(cascade = CascadeType.ALL) //remember to reverse it
//    @JoinTable(name = "address")
    @JoinColumn(name = "address_id")
    @NotNull
    @Getter
    @Setter
    private Address address;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "address")
//    @JoinColumn(name = "address_id")
//    @NotNull
//    @Getter
//    @Setter
//    private Address address;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "request_id")
//    @NotNull
//    @Getter
//    private ArrayList<Task> taskList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "request_id")
    //@NotNull
    @Getter
    private List<Task> taskList;

    @Column
    @NotNull
    @Getter
    private boolean accomplished;

//    @NotNull
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "request_id")
//    private ArrayList<Resource> resourceList = new ArrayList<>();

    /*@NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> resourceList = new ArrayList<>();*/
    /*@Getter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_resource",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Resource> resourceList;*/
    @Getter
    @Setter
    @OneToMany(mappedBy = "request")
    Set<RequestResource> resourceList;


    @Column
    @NotNull
    @Getter
    private LocalDate startDate;

    @Column
    @Getter
    private LocalDate endDate;

//    private ArrayList<Task> generateTasks() {
//        ArrayList<Task> tasks = new ArrayList<>();
//        for (Resource resource : resourceList) {
//            if (resource != null) {
//                ArrayList<String> status = new ArrayList<>();
//                int resourceAmount = resource.getAmount();
//                for (int i = 0; i < resourceAmount + 1; i++) {
//                    //change instead of last day giving final status, it will have 1 additional "day" that will represent that
//                    status.add("created");
//                }
//                tasks.add(new Task(resource, status));
//            }
//        }
//        return tasks;
//    }

    public ArrayList<Task> generateTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (RequestResource rr : resourceList) {
            if (rr != null) {
//                ArrayList<Status> statuses = new ArrayList<>();
//                int dayAmount = rr.getResource().getAmount(); //remember to change to days somehow
//                for (int i = 0; i < dayAmount + 1; i++) {
//                    //change instead of last day giving final status, it will have 1 additional "day" that will represent that
//                    statuses.add(Status.fromValue(1));
//                }
//                tasks.add(new Task(rr.getResource(), statuses));
                List<Day> statuses = new ArrayList<>();
                int dayAmount = rr.getResource().getAmount();
                for (int i = 0; i <= dayAmount; i++) {
                    //change instead of last day giving final status, it will have 1 additional "day"
                    //that will represent that
                    statuses.add(new Day(Status.fromValue(1), i));
                }
                tasks.add(new Task(rr.getResource(), statuses));
            }
        }
        return tasks;
    }

    public Request(Requester requester, Address address,/* Set<Resource> resourceList,*/ LocalDate endDate) {
        this.requester = requester;
        this.address = address;
//        this.taskList = generateTasks();
        this.accomplished = false;
//        this.resourceList = resourceList;
        this.resourceList = new HashSet<>();
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public Request() {
        this.startDate = LocalDate.now();
    }

    public boolean accomplishedCheck() {
        boolean accomplished = true;

        for (Task task : this.getTaskList()) {
            Optional<Day> lastDayEntry = task.getDaysList().stream()
                    .max(Comparator.comparingInt(Day::getDayIndex));

            if (lastDayEntry.isPresent()) {
                Status lastDayStatus = lastDayEntry.get().getStatus();
                //Check if the last day's status is CREATED, PENDING, or IN_PROGRESS
                if (lastDayStatus.equals(Status.fromValue(1)) || //CREATED
                        lastDayStatus.equals(Status.fromValue(2)) || //PENDING
                        lastDayStatus.equals(Status.fromValue(3))) { //IN_PROGRESS
                    accomplished = false;
                }
            }
        }

        return accomplished;
    }
//    public boolean accomplishedCheck() {
//        boolean accomplished = true;
//        for (Task task : this.getTaskList()) {
//            Status status = task.getDaysList().getLast();
//            if (status.equals(Status.fromValue(1)) || status.equals(Status.fromValue(2))
//                    || status.equals(Status.fromValue(3))) {
//                accomplished = false;
//            }
//        }
//        return accomplished;
//    }
}

//package com.example.skph.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@jakarta.persistence.Entity
//@Table(name="request")
//public class Request {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String requesterFirstName;
//    private String requesterLastName;
//    private String address;
//
//    // Możesz zdefiniować pole typu String, aby przechować wybór typu zasobu
//    @ElementCollection
//    private List<String> resourceList;
//
//    // Możesz również zdefiniować inne pola, np. dla różnych typów zasobów
//    private String foodTemperature;
//    private boolean foodAllergyFree;
//    private String clothesSize;
//    private String clothesSex;
//    private boolean shelterWithAnimals;
//    private String otherDescription;
//        @Column
//    @NotNull
//    @Getter
//    private LocalDate startDate;
//
//    @Column
//    @Getter
//    private LocalDate endDate;
//
//    // Gettery i settery
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getRequesterFirstName() {
//        return requesterFirstName;
//    }
//
//    public void setRequesterFirstName(String requesterFirstName) {
//        this.requesterFirstName = requesterFirstName;
//    }
//
//    public String getRequesterLastName() {
//        return requesterLastName;
//    }
//
//    public void setRequesterLastName(String requesterLastName) {
//        this.requesterLastName = requesterLastName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public List<String> getResourceList() {
//        return resourceList;
//    }
//
//    public void setResourceList(List<String> resourceList) {
//        this.resourceList = resourceList;
//    }
//
//    public String getFoodTemperature() {
//        return foodTemperature;
//    }
//
//    public void setFoodTemperature(String foodTemperature) {
//        this.foodTemperature = foodTemperature;
//    }
//
//    public boolean isFoodAllergyFree() {
//        return foodAllergyFree;
//    }
//
//    public void setFoodAllergyFree(boolean foodAllergyFree) {
//        this.foodAllergyFree = foodAllergyFree;
//    }
//
//    public String getClothesSize() {
//        return clothesSize;
//    }
//
//    public void setClothesSize(String clothesSize) {
//        this.clothesSize = clothesSize;
//    }
//
//    public String getClothesSex() {
//        return clothesSex;
//    }
//
//    public void setClothesSex(String clothesSex) {
//        this.clothesSex = clothesSex;
//    }
//
//    public boolean isShelterWithAnimals() {
//        return shelterWithAnimals;
//    }
//
//    public void setShelterWithAnimals(boolean shelterWithAnimals) {
//        this.shelterWithAnimals = shelterWithAnimals;
//    }
//
//    public String getOtherDescription() {
//        return otherDescription;
//    }
//
//    public void setOtherDescription(String otherDescription) {
//        this.otherDescription = otherDescription;
//    }
//}

