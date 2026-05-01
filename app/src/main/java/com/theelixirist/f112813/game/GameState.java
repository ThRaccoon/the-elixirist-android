package com.theelixirist.f112813.game;

import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.database.dtos.UpgradeDto;
import com.theelixirist.f112813.database.mappers.EffectMapper;
import com.theelixirist.f112813.database.mappers.CatalystMapper;
import com.theelixirist.f112813.database.mappers.GeneratorMapper;
import com.theelixirist.f112813.database.mappers.UpgradeMapper;
import com.theelixirist.f112813.database.repositories.EffectRepository;
import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.database.repositories.GeneratorRepository;
import com.theelixirist.f112813.database.repositories.UpgradeRepository;
import com.theelixirist.f112813.game.templates.EffectTemplate;
import com.theelixirist.f112813.game.templates.TemplateRegistry;
import com.theelixirist.f112813.game.templates.GeneratorTemplate;
import com.theelixirist.f112813.game.templates.UpgradeTemplate;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.runtime.Effect;
import com.theelixirist.f112813.game.runtime.Catalyst;
import com.theelixirist.f112813.game.runtime.Generator;
import com.theelixirist.f112813.game.runtime.Upgrade;

import java.util.HashMap;

public class GameState {
    private BigDouble currentElixirs;

    private final HashMap<Integer, Generator> generators = new HashMap<>();
    private final HashMap<Integer, Upgrade> upgrades = new HashMap<>();
    private final HashMap<Integer, Catalyst> catalysts = new HashMap<>();
    private final HashMap<Integer, Effect> buffs = new HashMap<>();

    private final TemplateRegistry templateRegistry;

    private final GeneratorRepository generatorRepository;
    private final UpgradeRepository upgradeRepository;
    private final CatalystRepository catalystRepository;
    private final EffectRepository effectRepository;

    public GameState(
            TemplateRegistry templateRegistry,
            GeneratorRepository generatorRepository,
            UpgradeRepository upgradeRepository,
            CatalystRepository catalystRepository,
            EffectRepository effectRepository
    ) {
        this.templateRegistry = templateRegistry;
        this.generatorRepository = generatorRepository;
        this.upgradeRepository = upgradeRepository;
        this.catalystRepository = catalystRepository;
        this.effectRepository = effectRepository;
    }

    public BigDouble getCurrentElixirs() {
        return currentElixirs;
    }

    public void setCurrentElixirs(BigDouble currentElixirs) {
        this.currentElixirs = currentElixirs;
    }

    public HashMap<Integer, Generator> getGenerators() {
        return generators;
    }

    public HashMap<Integer, Upgrade> getUpgrades() {
        return upgrades;
    }

    public HashMap<Integer, Catalyst> getCatalysts() {
        return catalysts;
    }

    public HashMap<Integer, Effect> getBuffs() {
        return buffs;
    }

    public Generator getGenerator(int id) {
        return generators.get(id);
    }

    public Upgrade getUpgrade(int id) {
        return upgrades.get(id);
    }

    public Catalyst getCatalyst(int id) {
        return catalysts.get(id);
    }

    public Effect getBuff(int id) {
        return buffs.get(id);
    }

    public void putGenerator(Generator generator) {
        generators.put(generator.getId(), generator);
    }

    public void putUpgrade(Upgrade upgrade) {
        upgrades.put(upgrade.getId(), upgrade);
    }

    public void putCatalyst(Catalyst catalyst) {
        catalysts.put(catalyst.getId(), catalyst);
    }

    public void putBuff(Effect effect) {
        buffs.put(effect.getId(), effect);
    }

    public void addElixirs(BigDouble amount) {
        currentElixirs.add(amount);
    }

    public BigDouble computeElixirsPerClick() {
        double multiplier = 1.0;

        for (Upgrade upgrade : upgrades.values()) {
            UpgradeTemplate def = templateRegistry.getUpgradeDefinition(upgrade.getId());
            if (def == null) continue;
            if (def.affectsClick) multiplier *= def.yieldMultiplier;
        }

        for (Effect effect : buffs.values()) {
            EffectTemplate def = templateRegistry.getBuffDefinition(effect.getId());
            if (def == null) continue;
            if (def.affectsClick) multiplier *= def.yieldMultiplier;
        }

        return new BigDouble(multiplier, 0);
    }

    public boolean spendElixirs(BigDouble amount) {
        if (currentElixirs.compareTo(amount) < 0) return false;
        currentElixirs.subtract(amount);
        return true;
    }

    public BigDouble computeElixirsPerSecond() {
        BigDouble total = new BigDouble(0, 0);

        for (Generator generator : generators.values()) {
            GeneratorTemplate def = templateRegistry.getGeneratorDefinition(generator.getId());
            if (def == null) continue;

            BigDouble yield = new BigDouble(def.yieldPerSecond);
            yield.multiply(new BigDouble(generator.getCurrentCount(), 0));

            double multiplier = 1.0;

            for (Upgrade upgrade : upgrades.values()) {
                UpgradeTemplate uDef = templateRegistry.getUpgradeDefinition(upgrade.getId());
                if (uDef == null) continue;
                if (uDef.affectsAllGenerators || uDef.affectedGeneratorIds.contains(generator.getId())) {
                    multiplier *= uDef.yieldMultiplier;
                }
            }

            for (Effect effect : buffs.values()) {
                EffectTemplate bDef = templateRegistry.getBuffDefinition(effect.getId());
                if (bDef == null) continue;
                if (bDef.affectsAllGenerators || bDef.affectedGeneratorIds.contains(generator.getId())) {
                    multiplier *= bDef.yieldMultiplier;
                }
            }

            yield.multiply(new BigDouble(multiplier, 0));
            total.add(yield);
        }

        return total;
    }

    public void loadFromDatabase() {
        for (GeneratorDto dto : generatorRepository.readAll()) {
            generators.put(dto.id, GeneratorMapper.toRuntime(dto));
        }

        for (UpgradeDto dto : upgradeRepository.readAll()) {
            upgrades.put(dto.id, UpgradeMapper.toRuntime(dto));
        }

        for (CatalystDto dto : catalystRepository.readAll()) {
            catalysts.put(dto.id, CatalystMapper.toRuntime(dto));
            catalystRepository.delete(dto);
        }

        for (EffectDto dto : effectRepository.readAll()) {
            buffs.put(dto.id, EffectMapper.toRuntime(dto));
            effectRepository.delete(dto);
        }
    }

    public void saveToDatabase() {
        for (Catalyst catalyst : catalysts.values()) {
            catalystRepository.create(CatalystMapper.fromRuntime(catalyst));
        }

        for (Effect effect : buffs.values()) {
            effectRepository.create(EffectMapper.fromRuntime(effect));
        }
    }
}
