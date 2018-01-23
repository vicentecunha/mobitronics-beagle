package com.vcc.vicente.mobitronics;

enum SempraMode {
    DESLIGADO((byte) 0),
    MANUAL((byte) 1),
    AUTOMATICO((byte) 2);

    public byte value;
    SempraMode(byte value) {
        this.value = value;
    }
}
