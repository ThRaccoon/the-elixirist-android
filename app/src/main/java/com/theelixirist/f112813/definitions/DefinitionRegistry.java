package com.theelixirist.f112813.definitions;

import com.theelixirist.f112813.definitions.registries.CatalystRegistry;
import com.theelixirist.f112813.definitions.registries.EffectRegistry;
import com.theelixirist.f112813.definitions.registries.GeneratorRegistry;
import com.theelixirist.f112813.definitions.registries.UpgradeRegistry;

public class DefinitionRegistry {
    private final GeneratorRegistry generatorRegistry;
    private final UpgradeRegistry upgradeRegistry;
    private final CatalystRegistry catalystRegistry;
    private final EffectRegistry effectRegistry;

    public DefinitionRegistry(
            GeneratorRegistry generatorRegistry,
            UpgradeRegistry upgradeRegistry,
            CatalystRegistry catalystRegistry,
            EffectRegistry effectRegistry
    ) {
        this.generatorRegistry = generatorRegistry;
        this.upgradeRegistry = upgradeRegistry;
        this.catalystRegistry = catalystRegistry;
        this.effectRegistry = effectRegistry;
    }

    public GeneratorRegistry getGeneratorRegistry() {
        return generatorRegistry;
    }

    public UpgradeRegistry getUpgradeRegistry() {
        return upgradeRegistry;
    }

    public CatalystRegistry getCatalystRegistry() {
        return catalystRegistry;
    }

    public EffectRegistry getEffectRegistry() {
        return effectRegistry;
    }
}
