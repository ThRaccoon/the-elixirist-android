package com.theelixirist.f112813.game.models;

import java.util.Set;

public class Catalyst {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private long despawnDurationMs;

    private final Set<Integer> buffPoolIds;

    private float normalizedPosX;

    private float normalizedPosY;

    public Catalyst(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            long despawnDurationMs,
            Set<Integer> buffPoolIds
    ) {
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.despawnDurationMs = despawnDurationMs;
        this.buffPoolIds = buffPoolIds;
    }

    public int getId() {
        return id;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getNameResId() {
        return nameResId;
    }

    public int getDescResId() {
        return descResId;
    }

    public long getDespawnDurationMs() {
        return despawnDurationMs;
    }

    public void setDespawnDurationMs(long despawnDurationMs) {
        this.despawnDurationMs = despawnDurationMs;
    }

    public Set<Integer> getBuffPoolIds() {
        return buffPoolIds;
    }

    public float getNormalizedPosX() {
        return normalizedPosX;
    }

    public void setNormalizedPosX(float normalizedPosX) {
        this.normalizedPosX = normalizedPosX;
    }

    public float getNormalizedPosY() {
        return normalizedPosY;
    }

    public void setNormalizedPosY(float normalizedPosY) {
        this.normalizedPosY = normalizedPosY;
    }
}
