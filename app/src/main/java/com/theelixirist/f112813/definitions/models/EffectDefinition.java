package com.theelixirist.f112813.definitions.models;

import com.theelixirist.f112813.game.runtime.requirement.Requirement;

import java.util.List;
import java.util.Set;

public class EffectDefinition {
    public int id;

    public String icon;
    public String name;
    public String desc;
    public double rollWeight;
    public long durationMs;
    public double yieldMultiplier;
    public boolean affectsClick;
    public boolean affectsAllGenerators;
    public Set<Integer> affectedGeneratorIds;
    public List<Requirement> requirements;
}
