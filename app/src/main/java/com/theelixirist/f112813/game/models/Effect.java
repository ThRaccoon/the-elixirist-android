package com.theelixirist.f112813.game.models;

import java.util.Set;

public class Effect {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private final double multiplier;

    private long durationMs;
    private final boolean permanentDuration;

    private final boolean affectsClick;
    private final boolean affectsAllGenerators;
    private final Set<Integer> affectedGeneratorIds;

    public Effect(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            double multiplier,
            long durationMs,
            boolean permanentDuration,
            boolean affectsClick,
            boolean affectsAllGenerators,
            Set<Integer> affectedGeneratorIds
    ){
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.multiplier = multiplier;
        this.durationMs = durationMs;
        this.permanentDuration = permanentDuration;
        this.affectsClick = affectsClick;
        this.affectsAllGenerators = affectsAllGenerators;
        this.affectedGeneratorIds = affectedGeneratorIds;
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

    public double getMultiplier() {
        return multiplier;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public boolean isPermanentDuration() {
        return permanentDuration;
    }

    public boolean isAffectsClick() {
        return affectsClick;
    }

    public boolean isAffectsAllGenerators() {
        return affectsAllGenerators;
    }

    public Set<Integer> getAffectedGeneratorIds() {
        return affectedGeneratorIds;
    }
}
