package com.theelixirist.f112813.game.templates;

import com.theelixirist.f112813.game.runtime.Requirement;

import java.util.List;
import java.util.Set;

public class UpgradeTemplate {
    public int id;

    public String icon;
    public String name;
    public String desc;

    public String cost;

    public double yieldMultiplier;

    public boolean affectsClick;
    public boolean affectsAllGenerators;
    public Set<Integer> affectedGeneratorIds;

    public List<Requirement> requirements;
}
