package com.theelixirist.f112813.save.handlers;

import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.domain.mappers.CatalystDomainMapper;
import com.theelixirist.f112813.domain.models.Catalyst;
import com.theelixirist.f112813.domain.registries.CatalystRegistry;
import com.theelixirist.f112813.save.Saveable;

import java.util.HashMap;

public class CatalystSaveHandler implements Saveable<HashMap<Integer, Catalyst>> {
    private CatalystRegistry catalystRegistry;
    private final CatalystRepository catalystRepository;

    public CatalystSaveHandler(CatalystRepository catalystRepository) {
        this.catalystRepository = catalystRepository;
    }

    public void setCatalystRegistry(CatalystRegistry catalystRegistry) {
        this.catalystRegistry = catalystRegistry;
    }

    @Override
    public HashMap<Integer, Catalyst> load() {
        HashMap<Integer, Catalyst> catalysts = new HashMap<>();
        for (CatalystDto dto : catalystRepository.readAll()) {
            catalysts.put(dto.id, CatalystDomainMapper.toDomain(dto));
        }
        return catalysts;
    }

    @Override
    public void save() {
        catalystRepository.upsertAll(
                CatalystDomainMapper.toDtos(catalystRegistry.getCatalysts().values())
        );
    }

    public void create(Catalyst catalyst) {
        catalystRepository.create(CatalystDomainMapper.toDto(catalyst));
    }

    public void delete(Catalyst catalyst) {
        catalystRepository.delete(CatalystDomainMapper.toDto(catalyst));
    }
}
