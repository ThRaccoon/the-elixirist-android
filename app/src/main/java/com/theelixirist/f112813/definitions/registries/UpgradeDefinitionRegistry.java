package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.UpgradeDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class UpgradeDefinitionRegistry {
    private final HashMap<Integer, UpgradeDefinition> upgradeDefinitions = new HashMap<>();

    public UpgradeDefinitionRegistry(Context context) {
        DefinitionLoader.load(context, "upgrades.json", UpgradeDefinition.class)
                .forEach(u -> upgradeDefinitions.put(u.id, u));
    }

    public HashMap<Integer, UpgradeDefinition> getUpgradeDefinitions() {
        return upgradeDefinitions;
    }

    public UpgradeDefinition getUpgradeDefinition(int id) {
        return upgradeDefinitions.get(id);
    }
}
