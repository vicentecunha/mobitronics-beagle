package com.vcc.vicente.mobitronics;

public enum LoadType {
    CONSTANT_LOAD(0),
    CONSTANT_TORQUE(1);

    private int value;
    LoadType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
