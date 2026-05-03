package com.theelixirist.f112813.save.handlers;

import com.theelixirist.f112813.database.dtos.UpgradeDto;
import com.theelixirist.f112813.database.repositories.UpgradeRepository;
import com.theelixirist.f112813.domain.mappers.UpgradeDomainMapper;
import com.theelixirist.f112813.domain.models.Upgrade;
import com.theelixirist.f112813.domain.registries.UpgradeRegistry;
import com.theelixirist.f112813.save.Saveable;

import java.util.HashMap;

public class UpgradeSaveHandler implements Saveable<HashMap<Integer, Upgrade>> {
    private UpgradeRegistry upgradeRegistry;
    private final UpgradeRepository upgradeRepository;

    public UpgradeSaveHandler(UpgradeRepository upgradeRepository) {
        this.upgradeRepository = upgradeRepository;
    }

    public void setUpgradeRegistry(UpgradeRegistry upgradeRegistry) {
        this.upgradeRegistry = upgradeRegistry;
    }

    @Override
    public HashMap<Integer, Upgrade> load() {
        HashMap<Integer, Upgrade> upgrades = new HashMap<>();
        for (UpgradeDto dto : upgradeRepository.readAll()) {
            upgrades.put(dto.id, UpgradeDomainMapper.toDomain(dto));
        }
        return upgrades;
    }

    @Override
    public void save() {
    }

    public void create(Upgrade upgrade) {
        upgradeRepository.create(UpgradeDomainMapper.toDto(upgrade));
    }
}
