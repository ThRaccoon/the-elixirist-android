package com.theelixirist.f112813.game.data.definitions;

import com.theelixirist.f112813.game.core.Requirement;

import java.util.List;
import java.util.Set;

public class BuffDefinition {
    public int id;

    public String icon;
    public String name;
    public String desc;

    public float durationSec;

    public float yieldMultiplier;

    public boolean affectsClick;
    public boolean affectsAllGenerators;
    public Set<Integer> affectedGeneratorIds;

    public List<Requirement> requirements;
}
