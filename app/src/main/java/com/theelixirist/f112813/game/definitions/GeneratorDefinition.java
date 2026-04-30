package com.theelixirist.f112813.game.definitions;

import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.runtime.Requirement;

import java.util.List;

public class GeneratorDefinition {
    public int id;

    public String icon;
    public String name;
    public String desc;

    public String baseCost;
    public double costGrowthRate;

    public String yieldPerSecond;

    public List<Requirement> requirements;
}
