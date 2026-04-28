package com.theelixirist.f112813.game.definitions;

import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.runtime.Requirement;

import java.util.List;
import java.util.Set;

public class UpgradeDefinition {
    public int id;

    public int iconResId;
    public int nameResId;
    public int descResId;

    public BigDouble cost;

    public double yieldMultiplier;

    public boolean affectsClick;
    public boolean affectsAllGenerators;
    public Set<Integer> affectedGeneratorIds;

    public List<Requirement> requirements;
}
