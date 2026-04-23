package com.theelixirist.f112813.game.data.models;

import com.theelixirist.f112813.game.core.Requirement;
import com.theelixirist.f112813.game.math.BigDouble;

import java.util.List;

public class Generator {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private final BigDouble baseCost;
    private final float costGrowthRate;

    private final BigDouble baseYieldPerSecond;

    private final List<Requirement> requirements;

    private int ownedCount;

    public Generator(
            int id,
            int iconResId,
            int nameResId,
            int descResId,
            BigDouble baseCost,
            float costGrowthRate,
            BigDouble baseYieldPerSecond,
            List<Requirement> requirements,
            int ownedCount
    ) {
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.baseCost = baseCost;
        this.costGrowthRate = costGrowthRate;
        this.baseYieldPerSecond = baseYieldPerSecond;
        this.requirements = requirements;
        this.ownedCount = ownedCount;
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

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public int getOwnedCount() {
        return ownedCount;
    }

    public void setOwnedCount(int ownedCount) {
        this.ownedCount = ownedCount;
    }
}
