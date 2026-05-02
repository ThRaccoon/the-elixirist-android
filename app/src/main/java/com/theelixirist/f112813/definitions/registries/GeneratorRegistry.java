package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.GeneratorDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class GeneratorRegistry {
    private final HashMap<Integer, GeneratorDefinition> generatorTemplates = new HashMap<>();

    public GeneratorRegistry(Context context) {
        DefinitionLoader.load(context, "generators.json", GeneratorDefinition.class)
                .forEach(t -> generatorTemplates.put(t.id, t));
    }

    public HashMap<Integer, GeneratorDefinition> getGeneratorTemplates() {
        return generatorTemplates;
    }

    public GeneratorDefinition getGeneratorTemplate(int id) {
        return generatorTemplates.get(id);
    }
}
