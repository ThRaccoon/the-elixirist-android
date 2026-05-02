package com.theelixirist.f112813.definitions.registries;

import android.content.Context;

import com.theelixirist.f112813.definitions.models.GeneratorDefinition;
import com.theelixirist.f112813.definitions.DefinitionLoader;

import java.util.HashMap;

public class GeneratorDefinitionRegistry {
    private final HashMap<Integer, GeneratorDefinition> generatorDefinitions = new HashMap<>();

    public GeneratorDefinitionRegistry(Context context) {
        DefinitionLoader.load(context, "generators.json", GeneratorDefinition.class)
                .forEach(t -> generatorDefinitions.put(t.id, t));
    }

    public HashMap<Integer, GeneratorDefinition> getGeneratorDefinitions() {
        return generatorDefinitions;
    }

    public GeneratorDefinition getGeneratorDefinition(int id) {
        return generatorDefinitions.get(id);
    }
}
