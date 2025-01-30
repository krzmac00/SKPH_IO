package com.example.skph.DTO;

import com.example.skph.enums.Status;

import java.time.LocalDateTime;

public class DayDTO {
    private Status status;
    private LocalDateTime time;

    public DayDTO(Status status, int dayIndex, LocalDateTime time) {
        this.status = status;
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }


}
