package com.theelixirist.f112813.save.managers;

import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.domain.registries.CatalystRegistry;
import com.theelixirist.f112813.save.Saveable;

public class CatalystSaveManager implements Saveable {
    private final CatalystRegistry catalysts;
    private final CatalystRepository catalystRepository;

    public CatalystSaveManager(
            CatalystRegistry catalysts,
            CatalystRepository catalystRepository
    ) {
        this.catalysts = catalysts;
        this.catalystRepository = catalystRepository;
    }

    @Override
    public void save() {
        catalystRepository.
    }

    @Override
    public void load() {

    }
}
