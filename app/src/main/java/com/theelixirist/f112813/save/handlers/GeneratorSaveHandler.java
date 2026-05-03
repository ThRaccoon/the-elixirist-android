package com.theelixirist.f112813.save.handlers;

import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.database.repositories.GeneratorRepository;
import com.theelixirist.f112813.domain.mappers.GeneratorDomainMapper;
import com.theelixirist.f112813.domain.models.Generator;
import com.theelixirist.f112813.domain.registries.GeneratorRegistry;
import com.theelixirist.f112813.save.Saveable;

import java.util.HashMap;

public class GeneratorSaveHandler implements Saveable<HashMap<Integer, Generator>> {
    private GeneratorRegistry generatorRegistry;
    private final GeneratorRepository generatorRepository;

    public GeneratorSaveHandler(GeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }

    public void setGeneratorRegistry(GeneratorRegistry generatorRegistry) {
        this.generatorRegistry = generatorRegistry;
    }

    @Override
    public HashMap<Integer, Generator> load() {
        HashMap<Integer, Generator> generators = new HashMap<>();
        for (GeneratorDto dto : generatorRepository.readAll()) {
            generators.put(dto.id, GeneratorDomainMapper.toDomain(dto));
        }
        return generators;
    }

    @Override
    public void save() {
    }

    public void create(Generator generator) {
        generatorRepository.create(GeneratorDomainMapper.toDto(generator));
    }

    public void updateCurrentCountById(int id, int newCount) {
        generatorRepository.updateCurrentCountById(id, newCount);
    }
}
