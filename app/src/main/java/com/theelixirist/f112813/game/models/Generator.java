package com.theelixirist.f112813.game.models;

import com.theelixirist.f112813.game.math.BigDouble;

public class Generator {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private final BigDouble baseCost;
    private final float costGrowthRate;

    private final BigDouble baseYieldPerSecond;

    public Generator(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            BigDouble baseCost,
            float costGrowthRate,
            BigDouble baseYieldPerSecond
    ) {
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.baseCost = baseCost;
        this.costGrowthRate = costGrowthRate;
        this.baseYieldPerSecond = baseYieldPerSecond;
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

    public BigDouble getBaseYieldPerSecond() {
        return baseYieldPerSecond;
    }
}
