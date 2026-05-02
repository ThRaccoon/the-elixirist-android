package com.theelixirist.f112813.game.state;

import com.theelixirist.f112813.game.runtime.Generator;
import com.theelixirist.f112813.game.runtime.Upgrade;

import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, Generator> generators = new HashMap<>();
    private HashMap<Integer, Upgrade> upgrades = new HashMap<>();

    public Inventory(
            HashMap<Integer, Generator> generators,
            HashMap<Integer, Upgrade> upgrades
    ) {
        this.generators = generators;
        this.upgrades = upgrades;
    }

    public HashMap<Integer, Generator> getGenerators() {
        return generators;
    }

    public HashMap<Integer, Upgrade> getUpgrades() {
        return upgrades;
    }

    public Generator getGenerator(int id) {
        return generators.get(id);
    }

    public Upgrade getUpgrade(int id) {
        return upgrades.get(id);
    }

    public void putGenerator(Generator generator) {
        generators.put(generator.getId(), generator);
    }

    public void putUpgrade(Upgrade upgrade) {
        upgrades.put(upgrade.getId(), upgrade);
    }
}
