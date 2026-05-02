package com.theelixirist.f112813.domain.registries;

import com.theelixirist.f112813.domain.models.Upgrade;

import java.util.HashMap;

public class UpgradeRegistry {
    private final HashMap<Integer, Upgrade> upgrades;

    public UpgradeRegistry(HashMap<Integer, Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    public HashMap<Integer, Upgrade> getUpgrades() {
        return upgrades;
    }

    public Upgrade getUpgrade(int id) {
        return upgrades.get(id);
    }

    public void putUpgrade(Upgrade upgrade) {
        upgrades.put(upgrade.getId(), upgrade);
    }
}
