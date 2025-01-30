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


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    private ArrayList<Task> generateTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Resource resource : resourceList) {
            if (resource != null) {
                ArrayList<String> status = new ArrayList<>();
                int resourceAmount = resource.getAmount();
                for (int i = 0; i < resourceAmount + 1; i++) {
                    //change instead of last day giving final status, it will have 1 additional "day" that will represent that
                    status.add("created");
                }
                tasks.add(new Task(resource, status));
            }
        }
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

    public Request() {
        this.startDate = LocalDate.now();
    }

    public boolean accomplishedCheck() {
        boolean accomplished = true;
        for (Task task : this.getTaskList()) {
            String status = task.getDaysList().getLast();
            if (status.equals("created") || status.equals("pending") || status.equals("inProgress")) {
                accomplished = false;
            }
        }
        return accomplished;
    }
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

