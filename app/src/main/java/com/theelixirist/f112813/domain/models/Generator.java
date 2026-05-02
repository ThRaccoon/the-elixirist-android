package com.theelixirist.f112813.domain.models;

public class Generator {
    private final int id;
    private int currentCount;

    public Generator(
            int id,
            int currentCount
    ) {
        this.id = id;
        this.currentCount = currentCount;
    }

    public int getId() {
        return id;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }
}
