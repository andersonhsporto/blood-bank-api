package com.api.bloodbankapi.commons.enums;

public enum BloodGroup {

    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    S_GROUP("S+");

    private final String value;

    BloodGroup(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BloodGroup fromValue(String value) {
        for (BloodGroup bloodGroup : BloodGroup.values()) {
            if (bloodGroup.value.equals(value)) {
                return bloodGroup;
            }
        }
        throw new IllegalArgumentException("Unknown blood group: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
