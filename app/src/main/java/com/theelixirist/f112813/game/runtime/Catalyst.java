package com.theelixirist.f112813.game.runtime;

public class Catalyst {
    private final int id;

    private long despawnDurationMs;

    private final float normalizedPosX;
    private final float normalizedPosY;

    public Catalyst(
            int id,
            long despawnDurationMs,
            float normalizedPosX,
            float normalizedPosY
    ) {
        this.id = id;
        this.despawnDurationMs = despawnDurationMs;
        this.normalizedPosX = normalizedPosX;
        this.normalizedPosY = normalizedPosY;
    }

    public int getId() {
        return id;
    }

    public long getDespawnDurationMs() {
        return despawnDurationMs;
    }

    public void setDespawnDurationMs(long despawnDurationMs) {
        this.despawnDurationMs = despawnDurationMs;
    }

    public float getNormalizedPosX() {
        return normalizedPosX;
    }

    public float getNormalizedPosY() {
        return normalizedPosY;
    }
}
