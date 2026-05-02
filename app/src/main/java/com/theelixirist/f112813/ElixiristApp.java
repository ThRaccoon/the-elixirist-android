package com.theelixirist.f112813;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.theelixirist.f112813.database.AppDatabase;
import com.theelixirist.f112813.definitions.registries.CatalystDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.EffectDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.GeneratorDefinitionRegistry;
import com.theelixirist.f112813.definitions.registries.UpgradeDefinitionRegistry;
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
                new GeneratorDefinitionRegistry(this),
                new UpgradeDefinitionRegistry(this),
                new CatalystDefinitionRegistry(this),
                new EffectDefinitionRegistry(this)
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
