package com.theelixirist.f112813.game.runtime;

public class Generator {
    private final int id;

    private int ownedCount;

    public Generator(
            int id,
            int ownedCount
    ) {
        this.id = id;
        this.ownedCount = ownedCount;
    }

    public int getId() {
        return id;
    }

    public int getOwnedCount() {
        return ownedCount;
    }

    public void setOwnedCount(int ownedCount) {
        this.ownedCount = ownedCount;
    }
}
