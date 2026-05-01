package com.theelixirist.f112813.game.runtime;

public class Effect {
    private final int id;

    private long durationMs;

    public Effect(
            int id,
            long durationMs
    ) {
        this.id = id;
        this.durationMs = durationMs;
    }

    public int getId() {
        return id;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }
}
