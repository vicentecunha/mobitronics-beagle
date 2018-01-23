package com.vcc.vicente.mobitronics;

public class IsopowerPoint {

    private int duration;
    private int load;

    public IsopowerPoint(int duration, int load) {
        this.duration = duration;
        this.load = load;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}
