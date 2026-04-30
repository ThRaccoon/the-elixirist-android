package com.theelixirist.f112813;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.theelixirist.f112813.database.AppDatabase;
import com.theelixirist.f112813.database.repositories.BuffRepository;
import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.database.repositories.ChronicleRepository;
import com.theelixirist.f112813.database.repositories.GeneratorRepository;
import com.theelixirist.f112813.database.repositories.UpgradeRepository;
import com.theelixirist.f112813.game.Chronicle;
import com.theelixirist.f112813.game.GameState;
import com.theelixirist.f112813.game.definitions.DefinitionRegistry;
import com.theelixirist.f112813.game.managers.AudioManager;

public class ElixiristApp extends Application {
    private AppDatabase database;
    private DefinitionRegistry definitionRegistry;
    private GameState gameState;
    private Chronicle chronicle;
    private AudioManager audioManager;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(this, AppDatabase.class, "elixirist.db")
                .allowMainThreadQueries()
                .build();

        definitionRegistry = new DefinitionRegistry(this);

        gameState = new GameState(
                definitionRegistry,
                new GeneratorRepository(database.generatorDao()),
                new UpgradeRepository(database.upgradeDao()),
                new CatalystRepository(database.catalystDao()),
                new BuffRepository(database.buffDao())
        );
        gameState.loadFromDatabase();

        chronicle = new Chronicle(
                new ChronicleRepository(database.chronicleDao()),
                gameState
        );
        chronicle.loadFromDatabase();

        audioManager = new AudioManager(this);
    }

    public static ElixiristApp get(Context context) {
        return (ElixiristApp) context.getApplicationContext();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public DefinitionRegistry getDefinitionRegistry() {
        return definitionRegistry;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Chronicle getChronicle() {
        return chronicle;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }
}
