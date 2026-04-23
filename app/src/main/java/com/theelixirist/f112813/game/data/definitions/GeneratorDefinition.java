package com.theelixirist.f112813.game.data.definitions;

import com.theelixirist.f112813.game.core.Requirement;

import java.util.List;

public class GeneratorDefinition {
    public int id;

    public String icon;
    public String name;
    public String desc;

    public double baseCost;
    public float costGrowthRate;

    public double baseYieldPerSecond;

    public List<Requirement> requirements;
}
