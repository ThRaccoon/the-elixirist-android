package com.theelixirist.f112813.game.managers.save;

import com.theelixirist.f112813.database.repositories.UpgradeRepository;

public class UpgradeSaveManager {
    private final UpgradeRepository upgradeRepository;

    public UpgradeSaveManager(UpgradeRepository upgradeRepository) {
        this.upgradeRepository = upgradeRepository;
    }
}
