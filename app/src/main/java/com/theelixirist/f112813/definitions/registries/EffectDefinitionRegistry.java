package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.EffectDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class EffectDefinitionRegistry {
    private final HashMap<Integer, EffectDefinition> effectDefinitions = new HashMap<>();

    public EffectDefinitionRegistry(Context context) {
        DefinitionLoader.load(context, "effects.json", EffectDefinition.class)
                .forEach(e -> effectDefinitions.put(e.id, e));
    }

    public HashMap<Integer, EffectDefinition> getEffectDefinitions() {
        return effectDefinitions;
    }

    public EffectDefinition getEffectDefinition(int id) {
        return effectDefinitions.get(id);
    }
}
