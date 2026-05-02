package com.theelixirist.f112813.definitions;

import com.theelixirist.f112813.definitions.registries.CatalystDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.EffectDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.GeneratorDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.UpgradeDefinitionRegistry;

public class DefinitionRegistry {
    private final GeneratorDefinitionRegistry generatorDefinitionRegistry;
    private final UpgradeDefinitionRegistry upgradeDefinitionRegistry;
    private final CatalystDefinitionRegistry catalystDefinitionRegistry;
    private final EffectDefinitionRegistry effectDefinitionRegistry;

    public DefinitionRegistry(
            GeneratorDefinitionRegistry generatorDefinitionRegistry,
            UpgradeDefinitionRegistry upgradeDefinitionRegistry,
            CatalystDefinitionRegistry catalystDefinitionRegistry,
            EffectDefinitionRegistry effectDefinitionRegistry
    ) {
        this.generatorDefinitionRegistry = generatorDefinitionRegistry;
        this.upgradeDefinitionRegistry = upgradeDefinitionRegistry;
        this.catalystDefinitionRegistry = catalystDefinitionRegistry;
        this.effectDefinitionRegistry = effectDefinitionRegistry;
    }

    public GeneratorDefinitionRegistry getGeneratorDefinitionRegistry() {
        return generatorDefinitionRegistry;
    }

    public UpgradeDefinitionRegistry getUpgradeDefinitionRegistry() {
        return upgradeDefinitionRegistry;
    }

    public CatalystDefinitionRegistry getCatalystDefinitionRegistry() {
        return catalystDefinitionRegistry;
    }

    public EffectDefinitionRegistry getEffectDefinitionRegistry() {
        return effectDefinitionRegistry;
    }
}
