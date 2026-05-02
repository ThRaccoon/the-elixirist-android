package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.CatalystDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class CatalystRegistry {
    private final HashMap<Integer, CatalystDefinition> catalystTemplates = new HashMap<>();

    public CatalystRegistry(Context context) {
        DefinitionLoader.load(context, "catalysts.json", CatalystDefinition.class)
                .forEach(c -> catalystTemplates.put(c.id, c));
    }

    public HashMap<Integer, CatalystDefinition> getCatalystTemplates() {
        return catalystTemplates;
    }

    public CatalystDefinition getCatalystTemplate(int id) {
        return catalystTemplates.get(id);
    }
}
