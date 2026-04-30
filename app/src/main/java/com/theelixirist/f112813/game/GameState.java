package com.theelixirist.f112813.game;

import com.theelixirist.f112813.database.dtos.BuffDto;
import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.database.dtos.UpgradeDto;
import com.theelixirist.f112813.database.mappers.BuffMapper;
import com.theelixirist.f112813.database.mappers.CatalystMapper;
import com.theelixirist.f112813.database.mappers.GeneratorMapper;
import com.theelixirist.f112813.database.mappers.UpgradeMapper;
import com.theelixirist.f112813.database.repositories.BuffRepository;
import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.database.repositories.GeneratorRepository;
import com.theelixirist.f112813.database.repositories.UpgradeRepository;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.runtime.Buff;
import com.theelixirist.f112813.game.runtime.Catalyst;
import com.theelixirist.f112813.game.runtime.Generator;
import com.theelixirist.f112813.game.runtime.Upgrade;

import java.util.HashMap;

public class GameState {
    private BigDouble currentElixirs;

    private final GeneratorRepository generatorRepository;
    private final UpgradeRepository upgradeRepository;
    private final CatalystRepository catalystRepository;
    private final BuffRepository buffRepository;

    private final HashMap<Integer, Generator> generators = new HashMap<>();
    private final HashMap<Integer, Upgrade> upgrades = new HashMap<>();
    private final HashMap<Integer, Catalyst> catalysts = new HashMap<>();
    private final HashMap<Integer, Buff> buffs = new HashMap<>();

    public GameState(
            GeneratorRepository generatorRepository,
            UpgradeRepository upgradeRepository,
            CatalystRepository catalystRepository,
            BuffRepository buffRepository
    ) {
        this.generatorRepository = generatorRepository;
        this.upgradeRepository = upgradeRepository;
        this.catalystRepository = catalystRepository;
        this.buffRepository = buffRepository;
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

    public HashMap<Integer, Buff> getBuffs() {
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

    public Buff getBuff(int id) {
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

    public void putBuff(Buff buff) {
        buffs.put(buff.getId(), buff);
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

        for (BuffDto dto : buffRepository.readAll()) {
            buffs.put(dto.id, BuffMapper.toRuntime(dto));
            buffRepository.delete(dto);
        }
    }

    public void saveToDatabase() {
        for (Catalyst catalyst : catalysts.values()) {
            catalystRepository.create(CatalystMapper.fromRuntime(catalyst));
        }

        for (Buff buff : buffs.values()) {
            buffRepository.create(BuffMapper.fromRuntime(buff));
        }
    }
}
