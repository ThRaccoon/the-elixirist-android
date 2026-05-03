package com.theelixirist.f112813.game.systems;

import com.theelixirist.f112813.AppContainer;
import com.theelixirist.f112813.definitions.models.GeneratorDefinition;
import com.theelixirist.f112813.definitions.models.UpgradeDefinition;
import com.theelixirist.f112813.domain.models.Generator;
import com.theelixirist.f112813.domain.models.Upgrade;
import com.theelixirist.f112813.game.math.BigDouble;

public class PurchaseSystem {
    private final AppContainer appContainer;

    public PurchaseSystem(AppContainer appContainer) {
        this.appContainer = appContainer;
    }

    public void buyGenerator(GeneratorDefinition generatorDefinition) {
        BigDouble cost = calculateGeneratorCost(generatorDefinition);
        BigDouble currentElixirs = appContainer.getChronicle().getCurrentElixirs();

        if (currentElixirs.compareTo(cost) < 0) { return; }

        currentElixirs.subtract(cost);

        Generator existing = appContainer.getGeneratorRegistry().getGenerator(generatorDefinition.id);
        if (existing != null) {
            existing.setCurrentCount(existing.getCurrentCount() + 1);
            appContainer.getGeneratorSaveHandler().updateCurrentCountById(generatorDefinition.id, existing.getCurrentCount());
        } else {
            Generator generator = new Generator(generatorDefinition.id, 1);
            appContainer.getGeneratorRegistry().putGenerator(generator);
            appContainer.getGeneratorSaveHandler().create(generator);
        }
    }

    public void buyUpgrade(UpgradeDefinition upgradeDefinition) {
        BigDouble cost = new BigDouble(upgradeDefinition.cost);
        BigDouble currentElixirs = appContainer.getChronicle().getCurrentElixirs();

        if (currentElixirs.compareTo(cost) < 0) { return; }
        if (appContainer.getUpgradeRegistry().getUpgrades().containsKey(upgradeDefinition.id)) {
            return;
        }

        currentElixirs.subtract(cost);

        Upgrade upgrade = new Upgrade(upgradeDefinition.id);
        appContainer.getUpgradeRegistry().putUpgrade(upgrade);
        appContainer.getUpgradeSaveHandler().create(upgrade);
    }

    public BigDouble calculateGeneratorCost(GeneratorDefinition def) {
        Generator existing = appContainer.getGeneratorRegistry().getGenerator(def.id);
        int owned = existing != null ? existing.getCurrentCount() : 0;
        // cost = baseCost * costGrowthRate^owned
        double cost = Double.parseDouble(def.baseCost) * Math.pow(def.costGrowthRate, owned);
        return new BigDouble(cost);
    }
}
