package com.example.skph.enums;

public enum UserRole {

    VICTIM(1),
    VOLUNTEER(2),
    AID_ORGANIZATION(3),
    AUTHORITY_REPRESENTATIVE(4);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserRole fromValue(int value) {
        for (UserRole type : UserRole.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid UserRole value: " + value);
    }
}

