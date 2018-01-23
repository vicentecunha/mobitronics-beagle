package com.vcc.vicente.mobitronics;

public class Channel {
    private byte state = 1;
    private int intensity = 10;
    private int frequency = 50;
    private int width = 200;
    private int initialAngle = 0;
    private int finalAngle = 300;

    public Channel() {}

    public Channel(int intensity, int frequency, int width) {
        this.intensity = intensity;
        this.frequency = frequency;
        this.width = width;
    }

    public Channel(byte state, int intensity, int frequency, int width, int initialAngle, int finalAngle) {
        this.state = state;
        this.intensity = intensity;
        this.frequency = frequency;
        this.width = width;
        this.initialAngle = initialAngle;
        this.finalAngle = finalAngle;
    }

    byte[] getBytes() {
        return new byte[] {
                state,
                (byte) intensity,
                (byte) frequency,
                (byte) (width >> 8),
                (byte) width,
                (byte) (initialAngle >> 8),
                (byte) initialAngle,
                (byte) (finalAngle >> 8),
                (byte) finalAngle,
        };
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getInitialAngle() {
        return initialAngle;
    }

    public void setInitialAngle(int initialAngle) {
        this.initialAngle = initialAngle;
    }

    public int getFinalAngle() {
        return finalAngle;
    }

    public void setFinalAngle(int finalAngle) {
        this.finalAngle = finalAngle;
    }
}
