package com.theelixirist.f112813;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.theelixirist.f112813.database.AppDatabase;
import com.theelixirist.f112813.definitions.registries.CatalystRegistry;
import com.theelixirist.f112813.definitions.registries.EffectRegistry;
import com.theelixirist.f112813.definitions.registries.GeneratorRegistry;
import com.theelixirist.f112813.definitions.registries.UpgradeRegistry;
import com.theelixirist.f112813.game.managers.AudioManager;
import com.theelixirist.f112813.game.managers.SaveManager;
import com.theelixirist.f112813.game.state.GameState;
import com.theelixirist.f112813.definitions.DefinitionRegistry;

public class ElixiristApp extends Application {
    private AppDatabase database;
    private DefinitionRegistry definitionRegistry;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(this, AppDatabase.class, "elixirist.db")
                .allowMainThreadQueries()
                .build();

        definitionRegistry = new DefinitionRegistry(
                new GeneratorRegistry(this),
                new UpgradeRegistry(this),
                new CatalystRegistry(this),
                new EffectRegistry(this)
        );
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
}
