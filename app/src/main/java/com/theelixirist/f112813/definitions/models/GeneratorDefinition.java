package com.theelixirist.f112813.definitions.models;

import com.theelixirist.f112813.domain.Requirement;

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
