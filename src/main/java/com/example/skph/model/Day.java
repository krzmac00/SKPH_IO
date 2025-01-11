package com.example.skph.model;

import com.example.skph.enums.Status;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Day {
    @NotNull
    @Enumerated(EnumType.STRING) // Store Status as a string
    private Status status;

    @NotNull
    private int dayIndex; // Represents the day or sequence of the status

    public Day() {}

    public Day(Status status, int dayIndex) {
        this.status = status;
        this.dayIndex = dayIndex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }
}