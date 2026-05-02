package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.UpgradeDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class UpgradeRegistry {
    private final HashMap<Integer, UpgradeDefinition> upgradeTemplates = new HashMap<>();

    public UpgradeRegistry(Context context) {
        DefinitionLoader.load(context, "upgrades.json", UpgradeDefinition.class)
                .forEach(u -> upgradeTemplates.put(u.id, u));
    }

    public HashMap<Integer, UpgradeDefinition> getUpgradeTemplates() {
        return upgradeTemplates;
    }

    public UpgradeDefinition getUpgradeTemplate(int id) {
        return upgradeTemplates.get(id);
    }
}
