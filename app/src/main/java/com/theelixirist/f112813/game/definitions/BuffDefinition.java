package com.theelixirist.f112813.game.definitions;

import com.theelixirist.f112813.game.runtime.Requirement;

import java.util.List;
import java.util.Set;

public class BuffDefinition {
    public int id;

    public int icon;
    public int name;
    public int desc;

    public double rollWeight;
    public long durationMs;

    public double yieldMultiplier;

    public boolean affectsClick;
    public boolean affectsAllGenerators;
    public Set<Integer> affectedGeneratorIds;

    public List<Requirement> requirements;
}
