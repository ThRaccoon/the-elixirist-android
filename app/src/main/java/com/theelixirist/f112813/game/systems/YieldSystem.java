package com.theelixirist.f112813.game.systems;

import com.theelixirist.f112813.definitions.models.EffectDefinition;
import com.theelixirist.f112813.definitions.models.GeneratorDefinition;
import com.theelixirist.f112813.definitions.models.UpgradeDefinition;
import com.theelixirist.f112813.definitions.registries.EffectDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.GeneratorDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.UpgradeDefinitionRegistry;
import com.theelixirist.f112813.domain.models.Effect;
import com.theelixirist.f112813.domain.models.Generator;
import com.theelixirist.f112813.domain.models.Upgrade;
import com.theelixirist.f112813.domain.registries.EffectRegistry;
import com.theelixirist.f112813.domain.registries.GeneratorRegistry;
import com.theelixirist.f112813.domain.registries.UpgradeRegistry;
import com.theelixirist.f112813.game.math.BigDouble;

import java.util.Collection;

public class YieldSystem {
    private final GeneratorRegistry generatorRegistry;
    private final UpgradeRegistry upgradeRegistry;
    private final EffectRegistry effectRegistry;

    private final GeneratorDefinitionRegistry generatorDefinitionRegistry;
    private final UpgradeDefinitionRegistry upgradeDefinitionRegistry;
    private final EffectDefinitionRegistry effectDefinitionRegistry;

    public YieldSystem(
            GeneratorRegistry generatorRegistry,
            UpgradeRegistry upgradeRegistry,
            EffectRegistry effectRegistry,
            GeneratorDefinitionRegistry generatorDefinitionRegistry,
            UpgradeDefinitionRegistry upgradeDefinitionRegistry,
            EffectDefinitionRegistry effectDefinitionRegistry
    ) {
        this.generatorRegistry = generatorRegistry;
        this.upgradeRegistry = upgradeRegistry;
        this.effectRegistry = effectRegistry;
        this.generatorDefinitionRegistry = generatorDefinitionRegistry;
        this.upgradeDefinitionRegistry = upgradeDefinitionRegistry;
        this.effectDefinitionRegistry = effectDefinitionRegistry;
    }

    public BigDouble calculateYieldPerSecond() {
        Collection<Effect> effects = effectRegistry.getEffects().values();

        BigDouble total = new BigDouble(0, 0);

        for (Generator generator : generatorRegistry.getGenerators().values()) {
            GeneratorDefinition def = generatorDefinitionRegistry.getGeneratorDefinition(generator.getId());
            if (def == null) continue;

            BigDouble generatorYield = new BigDouble(def.yieldPerSecond);
            generatorYield.multiply(generator.getCurrentCount(), 0);

            for (Upgrade upgrade : upgradeRegistry.getUpgrades().values()) {
                UpgradeDefinition upgradeDef = upgradeDefinitionRegistry.getUpgradeDefinition(upgrade.getId());
                if (upgradeDef == null) continue;

                if (upgradeDef.affectsAllGenerators ||
                        (upgradeDef.affectedGeneratorIds != null &&
                                upgradeDef.affectedGeneratorIds.contains(generator.getId()))) {
                    generatorYield.multiply(upgradeDef.yieldMultiplier, 0);
                }
            }

            for (Effect effect : effects) {
                EffectDefinition effectDef = effectDefinitionRegistry.getEffectDefinition(effect.getId());
                if (effectDef == null) continue;

                if (effectDef.affectsAllGenerators ||
                        (effectDef.affectedGeneratorIds != null &&
                                effectDef.affectedGeneratorIds.contains(generator.getId()))) {
                    generatorYield.multiply(effectDef.yieldMultiplier, 0);
                }
            }
            total.add(generatorYield);
        }
        return total;
    }
}
