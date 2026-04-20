package com.theelixirist.f112813.game.models;

import java.util.Set;

public class Buff {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private float durationSec;

    private final float yieldMultiplier;

    private final boolean affectsClick;
    private final boolean affectsAllGenerators;
    private final Set<Integer> affectedGeneratorIds;

    public Buff(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            float durationSec,
            float yieldMultiplier,
            boolean affectsClick,
            boolean affectsAllGenerators,
            Set<Integer> affectedGeneratorIds
    ) {
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.durationSec = durationSec;
        this.yieldMultiplier = yieldMultiplier;
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

    public float getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(float durationSec) {
        this.durationSec = durationSec;
    }

    public float getYieldMultiplier() {
        return yieldMultiplier;
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
