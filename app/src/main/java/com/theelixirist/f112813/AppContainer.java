package com.theelixirist.f112813;

import android.content.Context;

import androidx.room.Room;

import com.theelixirist.f112813.database.AppDatabase;
import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.database.repositories.ChronicleRepository;
import com.theelixirist.f112813.database.repositories.EffectRepository;
import com.theelixirist.f112813.database.repositories.GeneratorRepository;
import com.theelixirist.f112813.database.repositories.UpgradeRepository;
import com.theelixirist.f112813.definitions.registries.CatalystDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.EffectDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.GeneratorDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.UpgradeDefinitionRegistry;
import com.theelixirist.f112813.domain.models.Chronicle;
import com.theelixirist.f112813.domain.registries.CatalystRegistry;
import com.theelixirist.f112813.domain.registries.EffectRegistry;
import com.theelixirist.f112813.domain.registries.GeneratorRegistry;
import com.theelixirist.f112813.domain.registries.UpgradeRegistry;
import com.theelixirist.f112813.save.SaveManager;
import com.theelixirist.f112813.save.handlers.CatalystSaveHandler;
import com.theelixirist.f112813.save.handlers.ChronicleSaveHandler;
import com.theelixirist.f112813.save.handlers.EffectSaveHandler;
import com.theelixirist.f112813.save.handlers.GeneratorSaveHandler;
import com.theelixirist.f112813.save.handlers.UpgradeSaveHandler;

public class AppContainer {
    private final GeneratorDefinitionRegistry generatorDefinitionRegistry;
    private final UpgradeDefinitionRegistry upgradeDefinitionRegistry;
    private final CatalystDefinitionRegistry catalystDefinitionRegistry;
    private final EffectDefinitionRegistry effectDefinitionRegistry;

    private final SaveManager saveManager;
    private final ChronicleSaveHandler chronicleSaveHandler;
    private final GeneratorSaveHandler generatorSaveHandler;
    private final UpgradeSaveHandler upgradeSaveHandler;
    private final CatalystSaveHandler catalystSaveHandler;
    private final EffectSaveHandler effectSaveHandler;

    private final Chronicle chronicle;
    private final GeneratorRegistry generatorRegistry;
    private final UpgradeRegistry upgradeRegistry;
    private final CatalystRegistry catalystRegistry;
    private final EffectRegistry effectRegistry;

    public AppContainer(Context context) {
        AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "elixirist.db")
                .allowMainThreadQueries()
                .build();

        ChronicleRepository chronicleRepository = new ChronicleRepository(database.chronicleDao());
        GeneratorRepository generatorRepository = new GeneratorRepository(database.generatorDao());
        UpgradeRepository upgradeRepository = new UpgradeRepository(database.upgradeDao());
        CatalystRepository catalystRepository = new CatalystRepository(database.catalystDao());
        EffectRepository effectRepository = new EffectRepository(database.effectDao());

        generatorDefinitionRegistry = new GeneratorDefinitionRegistry(context);
        upgradeDefinitionRegistry = new UpgradeDefinitionRegistry(context);
        catalystDefinitionRegistry = new CatalystDefinitionRegistry(context);
        effectDefinitionRegistry = new EffectDefinitionRegistry(context);

        chronicleSaveHandler = new ChronicleSaveHandler(chronicleRepository);
        generatorSaveHandler = new GeneratorSaveHandler(generatorRepository);
        upgradeSaveHandler = new UpgradeSaveHandler(upgradeRepository);
        catalystSaveHandler = new CatalystSaveHandler(catalystRepository);
        effectSaveHandler = new EffectSaveHandler(effectRepository);

        chronicle = chronicleSaveHandler.load();
        generatorRegistry = new GeneratorRegistry(generatorSaveHandler.load());
        upgradeRegistry = new UpgradeRegistry(upgradeSaveHandler.load());
        catalystRegistry = new CatalystRegistry(catalystSaveHandler.load());
        effectRegistry = new EffectRegistry(effectSaveHandler.load());

        chronicleSaveHandler.setChronicle(chronicle);
        generatorSaveHandler.setGeneratorRegistry(generatorRegistry);
        upgradeSaveHandler.setUpgradeRegistry(upgradeRegistry);
        catalystSaveHandler.setCatalystRegistry(catalystRegistry);
        effectSaveHandler.setEffectRegistry(effectRegistry);

        saveManager = new SaveManager();
        saveManager.registerHandler(catalystSaveHandler);
        saveManager.registerHandler(generatorSaveHandler);
        saveManager.registerHandler(upgradeSaveHandler);
        saveManager.registerHandler(effectSaveHandler);
        saveManager.registerHandler(chronicleSaveHandler);
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

    public Chronicle getChronicle() {
        return chronicle;
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

    public SaveManager getSaveManager() {
        return saveManager;
    }

    public ChronicleSaveHandler getChronicleSaveHandler() {
        return chronicleSaveHandler;
    }

    public GeneratorSaveHandler getGeneratorSaveHandler() {
        return generatorSaveHandler;
    }

    public UpgradeSaveHandler getUpgradeSaveHandler() {
        return upgradeSaveHandler;
    }

    public CatalystSaveHandler getCatalystSaveHandler() {
        return catalystSaveHandler;
    }

    public EffectSaveHandler getEffectSaveHandler() {
        return effectSaveHandler;
    }
}
