package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.EffectDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class EffectRegistry {
    private final HashMap<Integer, EffectDefinition> effectTemplates = new HashMap<>();

    public EffectRegistry(Context context) {
        DefinitionLoader.load(context, "effects.json", EffectDefinition.class)
                .forEach(e -> effectTemplates.put(e.id, e));
    }

    public HashMap<Integer, EffectDefinition> getEffectTemplates() {
        return effectTemplates;
    }

    public EffectDefinition getEffectTemplate(int id) {
        return effectTemplates.get(id);
    }
}
