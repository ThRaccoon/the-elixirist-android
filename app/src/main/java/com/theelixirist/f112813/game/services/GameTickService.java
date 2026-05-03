package com.theelixirist.f112813.game.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.theelixirist.f112813.AppContainer;
import com.theelixirist.f112813.ElixiristApp;
import com.theelixirist.f112813.definitions.models.CatalystDefinition;
import com.theelixirist.f112813.definitions.registries.CatalystDefinitionRegistry;
import com.theelixirist.f112813.domain.models.Catalyst;
import com.theelixirist.f112813.domain.models.Chronicle;
import com.theelixirist.f112813.domain.models.Effect;
import com.theelixirist.f112813.domain.registries.CatalystRegistry;
import com.theelixirist.f112813.domain.registries.EffectRegistry;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.systems.YieldSystem;
import com.theelixirist.f112813.save.SaveManager;
import com.theelixirist.f112813.save.handlers.CatalystSaveHandler;
import com.theelixirist.f112813.save.handlers.EffectSaveHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GameTickService extends Service {

    private static final long TICK_INTERVAL_MS = 1000L;
    private static final long SAVE_INTERVAL_MS = 60_000L;

    private static final long CATALYST_SPAWN_MIN_MS = 180_000L;
    private static final long CATALYST_SPAWN_MAX_MS = 300_000L;

    private Thread tickThread;
    private volatile boolean running = false;

    private YieldSystem yieldSystem;
    private Chronicle chronicle;
    private CatalystRegistry catalystRegistry;
    private EffectRegistry effectRegistry;
    private CatalystDefinitionRegistry catalystDefinitionRegistry;
    private SaveManager saveManager;
    private CatalystSaveHandler catalystSaveHandler;
    private EffectSaveHandler effectSaveHandler;

    private final Random random = new Random();
    private long msSinceLastSave = 0;
    private long msSinceCatalystCheck = 0;
    private long nextCatalystSpawnMs = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        AppContainer container = ((ElixiristApp) getApplication()).getAppContainer();

        yieldSystem = new YieldSystem(
                container.getGeneratorRegistry(),
                container.getUpgradeRegistry(),
                container.getEffectRegistry(),
                container.getGeneratorDefinitionRegistry(),
                container.getUpgradeDefinitionRegistry(),
                container.getEffectDefinitionRegistry()
        );

        chronicle = container.getChronicle();
        catalystRegistry = container.getCatalystRegistry();
        effectRegistry = container.getEffectRegistry();
        catalystDefinitionRegistry = container.getCatalystDefinitionRegistry();
        saveManager = container.getSaveManager();
        catalystSaveHandler = container.getCatalystSaveHandler();
        effectSaveHandler = container.getEffectSaveHandler();

        nextCatalystSpawnMs = rollNextCatalystSpawn();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!running) {
            running = true;
            tickThread = new Thread(this::runTickLoop, "GameTickThread");
            tickThread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        running = false;
        if (tickThread != null) {
            tickThread.interrupt();
        }
        saveManager.save();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void runTickLoop() {
        while (running && !Thread.currentThread().isInterrupted()) {
            long tickStart = System.currentTimeMillis();

            try {
                tick();
            } catch (Exception e) {
                e.printStackTrace();
            }

            long elapsed = System.currentTimeMillis() - tickStart;
            long sleepMs = TICK_INTERVAL_MS - elapsed;

            if (sleepMs > 0) {
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    private void tick() {
        tickYield();
        // tickEffects();
        // tickCatalysts();
        tickSave();
    }

    private void tickYield() {
        BigDouble yieldThisTick = yieldSystem.calculateYieldPerSecond();
        chronicle.getCurrentElixirs().add(yieldThisTick);
        chronicle.getTotalElixirsBrewed().add(yieldThisTick);
        chronicle.setYieldPerSecond(yieldThisTick);
    }

    private void tickEffects() {
        List<Effect> toExpire = new ArrayList<>();

        for (Effect effect : effectRegistry.getEffects().values()) {
            long remaining = effect.getDurationMs() - TICK_INTERVAL_MS;
            if (remaining <= 0) {
                toExpire.add(effect);
            } else {
                effect.setDurationMs(remaining);
            }
        }

        for (Effect effect : toExpire) {
            effectRegistry.removeEffect(effect);
            effectSaveHandler.delete(effect);
        }
    }

    private void tickCatalysts() {
        List<Catalyst> toDespawn = new ArrayList<>();

        for (Catalyst catalyst : catalystRegistry.getCatalysts().values()) {
            long remaining = catalyst.getDespawnDurationMs() - TICK_INTERVAL_MS;
            if (remaining <= 0) {
                toDespawn.add(catalyst);
            } else {
                catalyst.setDespawnDurationMs(remaining);
            }
        }

        for (Catalyst catalyst : toDespawn) {
            catalystRegistry.removeCatalyst(catalyst);
            catalystSaveHandler.delete(catalyst);
        }

        if (!catalystRegistry.getCatalysts().isEmpty()) return;

        msSinceCatalystCheck += TICK_INTERVAL_MS;

        if (msSinceCatalystCheck >= nextCatalystSpawnMs) {
            spawnCatalyst();
            msSinceCatalystCheck = 0;
            nextCatalystSpawnMs = rollNextCatalystSpawn();
        }
    }

    private void spawnCatalyst() {
        CatalystDefinition def = rollCatalystDefinition();
        if (def == null) return;

        float posX = random.nextFloat();
        float posY = random.nextFloat();

        Catalyst catalyst = new Catalyst(def.id, def.despawnDurationMs, posX, posY);
        catalystRegistry.putCatalyst(catalyst);
        catalystSaveHandler.create(catalyst);

        chronicle.setTotalCatalystsCollected(chronicle.getTotalCatalystsCollected() + 1);
    }

    private CatalystDefinition rollCatalystDefinition() {
        Collection<CatalystDefinition> defs = catalystDefinitionRegistry.getCatalystDefinitions().values();
        if (defs.isEmpty()) return null;

        double totalWeight = 0;
        for (CatalystDefinition def : defs) {
            totalWeight += def.rollWeight;
        }

        double roll = random.nextDouble() * totalWeight;
        double cumulative = 0;

        for (CatalystDefinition def : defs) {
            cumulative += def.rollWeight;
            if (roll < cumulative) return def;
        }

        return null;
    }

    private void tickSave() {
        msSinceLastSave += TICK_INTERVAL_MS;
        if (msSinceLastSave >= SAVE_INTERVAL_MS) {
            saveManager.save();
            msSinceLastSave = 0;
        }
    }

    private long rollNextCatalystSpawn() {
        return CATALYST_SPAWN_MIN_MS +
                (long) (random.nextDouble() * (CATALYST_SPAWN_MAX_MS - CATALYST_SPAWN_MIN_MS));
    }
}
