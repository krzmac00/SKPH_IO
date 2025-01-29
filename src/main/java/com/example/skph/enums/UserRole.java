package com.example.skph.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    VICTIM("victim"),
    VOLUNTEER("volunteer"),
    AID_ORGANIZATION("aid_organization"),
    AUTHORITY_REPRESENTATIVE("authotiry_representative"),
    DONOR("donor");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserRole fromValue(String value) {
        for (UserRole type : UserRole.values()) {
            if (type.value.equals(value)) { // Zmiana == na equals()
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid UserRole value: " + value);
    }

}

