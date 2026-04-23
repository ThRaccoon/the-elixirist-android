package com.theelixirist.f112813.game.data.models;

import com.theelixirist.f112813.game.core.Requirement;

import java.util.List;
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

    private final List<Requirement> requirements;

    public Buff(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            float durationSec,
            float yieldMultiplier,
            boolean affectsClick,
            boolean affectsAllGenerators,
            Set<Integer> affectedGeneratorIds,
            List<Requirement> requirements
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
        this.requirements = requirements;
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

    public List<Requirement> getRequirements() {
        return requirements;
    }
}
