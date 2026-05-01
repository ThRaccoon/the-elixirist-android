package com.theelixirist.f112813.game.state;

import com.theelixirist.f112813.game.runtime.Catalyst;
import com.theelixirist.f112813.game.runtime.Effect;
import com.theelixirist.f112813.game.runtime.Generator;
import com.theelixirist.f112813.game.runtime.Upgrade;

import java.util.HashMap;

public class Inventory {
    private final HashMap<Integer, Generator> generators = new HashMap<>();
    private final HashMap<Integer, Upgrade> upgrades = new HashMap<>();
    private final HashMap<Integer, Catalyst> catalysts = new HashMap<>();
    private final HashMap<Integer, Effect> effects = new HashMap<>();

    public HashMap<Integer, Generator> getGenerators() {
        return generators;
    }

    public HashMap<Integer, Upgrade> getUpgrades() {
        return upgrades;
    }

    public HashMap<Integer, Catalyst> getCatalysts() {
        return catalysts;
    }

    public HashMap<Integer, Effect> getEffects() {
        return effects;
    }

    public Generator getGenerator(int id) {
        return generators.get(id);
    }

    public Upgrade getUpgrade(int id) {
        return upgrades.get(id);
    }

    public Catalyst getCatalyst(int id) {
        return catalysts.get(id);
    }

    public Effect getEffect(int id) {
        return effects.get(id);
    }

    public void putGenerator(Generator generator) {
        generators.put(generator.getId(), generator);
    }

    public void putUpgrade(Upgrade upgrade) {
        upgrades.put(upgrade.getId(), upgrade);
    }

    public void putCatalyst(Catalyst catalyst) {
        catalysts.put(catalyst.getId(), catalyst);
    }

    public void putEffect(Effect effect) {
        effects.put(effect.getId(), effect);
    }
}
