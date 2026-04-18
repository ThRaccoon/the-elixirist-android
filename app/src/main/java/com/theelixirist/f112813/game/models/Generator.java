package com.theelixirist.f112813.game.models;

import com.theelixirist.f112813.game.math.BigDouble;

public class Generator {
    private final int id;

    private final int iconResId;
    private final int nameResId;
    private final int descResId;

    private final BigDouble basePrice;
    private final BigDouble baseYieldPerSecond;

    private final float costMultiplier;

    public Generator(int id, int iconResId, int nameResId, int descResId, BigDouble basePrice,
                     BigDouble baseYieldPerSecond, float costMultiplier) {
        this.id = id;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
        this.descResId = descResId;
        this.basePrice = basePrice;
        this.baseYieldPerSecond = baseYieldPerSecond;
        this.costMultiplier = costMultiplier;
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

    public BigDouble getBasePrice() {
        return basePrice;
    }

    public BigDouble getBaseYieldPerSecond() {
        return baseYieldPerSecond;
    }

    public float getCostMultiplier() {
        return costMultiplier;
    }
}
