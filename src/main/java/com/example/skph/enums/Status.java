package com.example.skph.enums;

public enum Status {

    created(1),     //Task has been created from request
    pending(2),     //Supplies have been allocated. Needs confirmation from volunteer
    inProgress(3),  //Supplies have been allocated and has been confirmed by volunteer
    completed(4),   //Task completion has been confirmed by volunteer
    closed(5),      //Task completion has been confirmed by organisation
    canceled(6),    //Task has been canceled
    failed(7);      //Failed to complete the task in time

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status fromValue(int value) {
        for (Status type : Status.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }
}
