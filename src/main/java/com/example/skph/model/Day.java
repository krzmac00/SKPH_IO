package com.example.skph.model;

import com.example.skph.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@jakarta.persistence.Entity
@Table(name = "day")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING) // Store Status as a string
    private Status status;

    @Setter
    @NotNull
    private int dayIndex; // Represents the day or sequence of the status

    @Setter
    private LocalDateTime time;

    public Day() {}

    public Day(Status status, int dayIndex) {
        this.status = status;
        this.dayIndex = dayIndex; //time is set to null until new status is set
    }

    public void setStatus(Status status) {
        this.status = status;
        this.time = LocalDateTime.now();
    }
}