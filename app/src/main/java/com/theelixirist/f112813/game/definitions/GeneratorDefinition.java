package com.theelixirist.f112813.game.definitions;

import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.runtime.Requirement;

import java.util.List;

public class GeneratorDefinition {
    public int id;

    public int iconResId;
    public int nameResId;
    public int descResId;

    public BigDouble baseCost;
    public double costGrowthRate;

    public BigDouble yieldPerSecond;

    public List<Requirement> requirements;
}
