package com.example.skph.model;

public enum LocationType {
    VICTIM(1, "red"),
    VOLUNTEER(2, "green"),
    AID_ORGANIZATION(3, "blue"),
    AUTHORITY_REPRESENTATIVE(4, "yellow"),
    DISASTER_AREA(5, "black");

    private final int value;
    private final String color;

    LocationType(int value,  String color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public static LocationType fromValue(int value) {
        for (LocationType type : LocationType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LocationType value: " + value);
    }
}
