package com.theelixirist.f112813.game.data.definitions;

import com.theelixirist.f112813.game.core.Requirement;

import java.util.List;
import java.util.Set;

public class UpgradeDefinition {
    public int id;

    public String icon;
    public String name;
    public String desc;

    public double baseCost;
    public float costGrowthRate;

    public float yieldMultiplier;

    public boolean affectsClick;
    public boolean affectsAllGenerators;
    public Set<Integer> affectedGeneratorIds;

    public List<Requirement> requirements;
}
