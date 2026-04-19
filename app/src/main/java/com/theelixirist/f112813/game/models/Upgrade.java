package com.theelixirist.f112813.game.models;

import com.theelixirist.f112813.game.math.BigDouble;

import java.util.Set;

public class Upgrade {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private final BigDouble baseCost;
    private final float costGrowthRate;

    private final float yieldMultiplier;

    private final boolean affectsClick;
    private final boolean affectsAllGenerators;
    private final Set<Integer> affectedGeneratorIds;

    public Upgrade(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            BigDouble baseCost,
            float costGrowthRate,
            float yieldMultiplier,
            boolean affectsClick,
            boolean affectsAllGenerators,
            Set<Integer> affectedGeneratorIds
    ) {
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.baseCost = baseCost;
        this.costGrowthRate = costGrowthRate;
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

    public BigDouble getBaseCost() {
        return baseCost;
    }

    public float getCostGrowthRate() {
        return costGrowthRate;
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
