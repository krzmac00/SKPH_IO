package com.example.skph.model;

public enum LocationType {
    VICTIM(1),
    VOLUNTEER(2),
    AID_ORGANIZATION(3),
    AUTHORITY_REPRESENTATIVE(4),
    DISASTER_AREA(5);

    private final int value;

    LocationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getDatabaseValue() {
        return value;
    }

    public static LocationType fromValue(int value) {
        for (LocationType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LocationType value: " + value);
    }

    public static LocationType fromDatabaseValue(int dbValue) {
        for (LocationType type : values()) {
            if (type.getValue() == dbValue) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LocationType database value: " + dbValue);
    }
}

