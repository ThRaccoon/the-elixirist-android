package com.theelixirist.f112813.domain.registries;

import com.theelixirist.f112813.domain.models.Generator;

import java.util.HashMap;

public class GeneratorRegistry {
    private final HashMap<Integer, Generator> generators;

    public GeneratorRegistry(HashMap<Integer, Generator> generators) {
        this.generators = generators;
    }

    public HashMap<Integer, Generator> getGenerators() {
        return generators;
    }

    public Generator getGenerator(int id) {
        return generators.get(id);
    }

    public void putGenerator(Generator generator) {
        generators.put(generator.getId(), generator);
    }
}
