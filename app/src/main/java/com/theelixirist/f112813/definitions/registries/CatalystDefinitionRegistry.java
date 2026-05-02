package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.CatalystDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class CatalystDefinitionRegistry {
    private final HashMap<Integer, CatalystDefinition> catalystDefinitions = new HashMap<>();

    public CatalystDefinitionRegistry(Context context) {
        DefinitionLoader.load(context, "catalysts.json", CatalystDefinition.class)
                .forEach(c -> catalystDefinitions.put(c.id, c));
    }

    public HashMap<Integer, CatalystDefinition> getCatalystDefinitions() {
        return catalystDefinitions;
    }

    public CatalystDefinition getCatalystDefinition(int id) {
        return catalystDefinitions.get(id);
    }
}
