package com.theelixirist.f112813.game.state;

import com.theelixirist.f112813.game.math.BigDouble;

public class Chronicle {
    private long firstPlayedAt;
    private long lastPlayedAt;
    private BigDouble currentElixirs;
    private BigDouble totalElixirsBrewed;
    private long totalCatalystsCollected;
    private long totalEffectsTriggered;

    public Chronicle(
            long firstPlayedAt,
            long lastPlayedAt,
            BigDouble currentElixirs,
            BigDouble totalElixirsBrewed,
            long totalCatalystsCollected,
            long totalEffectsTriggered
    ) {
        this.firstPlayedAt = firstPlayedAt;
        this.lastPlayedAt = lastPlayedAt;
        this.currentElixirs = currentElixirs;
        this.totalElixirsBrewed = totalElixirsBrewed;
        this.totalCatalystsCollected = totalCatalystsCollected;
        this.totalEffectsTriggered = totalEffectsTriggered;
    }

    public static Chronicle createNew() {
        return new Chronicle(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                BigDouble.ZERO,
                BigDouble.ZERO,
                0,
                0
        );
    }

    public long getFirstPlayedAt() {
        return firstPlayedAt;
    }

    public void setFirstPlayedAt(long firstPlayedAt) {
        this.firstPlayedAt = firstPlayedAt;
    }

    public long getLastPlayedAt() {
        return lastPlayedAt;
    }

    public void setLastPlayedAt(long lastPlayedAt) {
        this.lastPlayedAt = lastPlayedAt;
    }

    public BigDouble getCurrentElixirs() {
        return currentElixirs;
    }

    public void setCurrentElixirs(BigDouble currentElixirs) {
        this.currentElixirs = currentElixirs;
    }

    public BigDouble getTotalElixirsBrewed() {
        return totalElixirsBrewed;
    }

    public void setTotalElixirsBrewed(BigDouble totalElixirsBrewed) {
        this.totalElixirsBrewed = totalElixirsBrewed;
    }

    public long getTotalCatalystsCollected() {
        return totalCatalystsCollected;
    }

    public void setTotalCatalystsCollected(long totalCatalystsCollected) {
        this.totalCatalystsCollected = totalCatalystsCollected;
    }

    public long getTotalEffectsTriggered() {
        return totalEffectsTriggered;
    }

    public void setTotalEffectsTriggered(long totalEffectsTriggered) {
        this.totalEffectsTriggered = totalEffectsTriggered;
    }
}
