package com.theelixirist.f112813.game.managers;

import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.database.dtos.UpgradeDto;
import com.theelixirist.f112813.game.managers.save.CatalystSaveManager;
import com.theelixirist.f112813.game.managers.save.ChronicleSaveManager;
import com.theelixirist.f112813.game.managers.save.EffectSaveManager;
import com.theelixirist.f112813.game.managers.save.GeneratorSaveManager;
import com.theelixirist.f112813.game.managers.save.UpgradeSaveManager;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.runtime.Catalyst;
import com.theelixirist.f112813.game.runtime.Effect;
import com.theelixirist.f112813.game.runtime.Generator;
import com.theelixirist.f112813.game.runtime.Upgrade;
import com.theelixirist.f112813.game.state.ActiveCatalysts;
import com.theelixirist.f112813.game.state.ActiveEffects;
import com.theelixirist.f112813.game.state.Chronicle;
import com.theelixirist.f112813.game.state.GameState;
import com.theelixirist.f112813.game.state.Inventory;
import com.theelixirist.f112813.definitions.DefinitionRegistry;

import java.util.HashMap;

public class SaveManager {
    private final ChronicleSaveManager chronicleSaveManager;
    private final GeneratorSaveManager generatorSaveManager;
    private final UpgradeSaveManager upgradeSaveManager;
    private final CatalystSaveManager catalystSaveManager;
    private final EffectSaveManager effectSaveManager;

    public SaveManager(
            ChronicleSaveManager chronicleSaveManager,
            GeneratorSaveManager generatorSaveManager,
            UpgradeSaveManager upgradeSaveManager,
            CatalystSaveManager catalystSaveManager,
            EffectSaveManager effectSaveManager
    ) {
        this.chronicleSaveManager = chronicleSaveManager;
        this.generatorSaveManager = generatorSaveManager;
        this.upgradeSaveManager = upgradeSaveManager;
        this.catalystSaveManager = catalystSaveManager;
        this.effectSaveManager = effectSaveManager;
    }

    public GameState load(DefinitionRegistry definitionRegistry) {
        return new GameState(
                loadChronicle(),
                loadInventory(),
                loadActiveCatalysts(),
                loadActiveEffects(),
                definitionRegistry
        );
    }

    private Chronicle loadChronicle() {
        ChronicleDto dto = chronicleRepository.read();

        if (dto == null) {
            return Chronicle.createNew();
        }

        return new Chronicle(
                dto.firstPlayedAt,
                dto.lastPlayedAt,
                new BigDouble(dto.currentElixirs),
                new BigDouble(dto.totalElixirsBrewed),
                dto.totalCatalystsCollected,
                dto.totalEffectsTriggered
        );
    }

    private Inventory loadInventory() {
        HashMap<Integer, Generator> generators = new HashMap<>();
        for (GeneratorDto dto : generatorRepository.readAll()) {
            generators.put(dto.id, new Generator(
                    dto.id,
                    dto.currentCount
            ));
        }

        HashMap<Integer, Upgrade> upgrades = new HashMap<>();
        for (UpgradeDto dto : upgradeRepository.readAll()) {
            upgrades.put(dto.id, new Upgrade(
                    dto.id
            ));
        }

        return new Inventory(
                generators,
                upgrades
        );
    }

    private ActiveCatalysts loadActiveCatalysts() {
        HashMap<Integer, Catalyst> catalysts = new HashMap<>();
        for (CatalystDto dto : catalystRepository.readAll()) {
            catalysts.put(dto.id, new Catalyst(
                    dto.id,
                    dto.despawnDurationMs,
                    dto.normalizedPosX,
                    dto.normalizedPosY
            ));
        }

        return new ActiveCatalysts(
                catalysts
        );
    }

    private ActiveEffects loadActiveEffects() {
        HashMap<Integer, Effect> effects = new HashMap<>();
        for (EffectDto dto : effectRepository.readAll()) {
            effects.put(dto.id, new Effect(
                    dto.id,
                    dto.durationMs
            ));
        }

        return new ActiveEffects(
                effects
        );
    }
}
