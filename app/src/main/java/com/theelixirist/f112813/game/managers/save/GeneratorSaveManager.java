package com.theelixirist.f112813.game.managers.save;

import com.theelixirist.f112813.database.repositories.GeneratorRepository;

public class GeneratorSaveManager {
    private final GeneratorRepository generatorRepository;

    public GeneratorSaveManager(GeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }
}
