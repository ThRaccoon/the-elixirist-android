package com.theelixirist.f112813.save.handlers;

import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.domain.mappers.CatalystDomainMapper;
import com.theelixirist.f112813.domain.models.Catalyst;
import com.theelixirist.f112813.domain.registries.CatalystRegistry;
import com.theelixirist.f112813.save.Saveable;

import java.util.HashMap;
import java.util.Map;

public class CatalystSaveHandler implements Saveable<Map<Integer, Catalyst>> {
    private final CatalystRegistry catalystRegistry;
    private final CatalystRepository catalystRepository;

    public CatalystSaveHandler(
            CatalystRegistry catalystRegistry,
            CatalystRepository catalystRepository
    ) {
        this.catalystRegistry = catalystRegistry;
        this.catalystRepository = catalystRepository;
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
