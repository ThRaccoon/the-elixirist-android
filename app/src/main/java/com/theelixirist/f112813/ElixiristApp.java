package com.theelixirist.f112813;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.theelixirist.f112813.database.AppDatabase;
import com.theelixirist.f112813.game.registries.DefinitionRegistry;
import com.theelixirist.f112813.game.managers.AudioManager;

public class ElixiristApp extends Application {
    private AppDatabase database;

    // Managers
    private AudioManager audioManager;

    // Registries
    private DefinitionRegistry definitionRegistry;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(this, AppDatabase.class, "elixirist.db")
                .allowMainThreadQueries()
                .build();

        // Managers
        audioManager = new AudioManager(this);

        // Registries
        definitionRegistry = new DefinitionRegistry(this);
    }

    public static ElixiristApp get(Context context) {
        return (ElixiristApp) context.getApplicationContext();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    // Managers
    public AudioManager getAudioManager() {
        return audioManager;
    }

    // Registries
    public DefinitionRegistry getDefinitionRegistry() {
        return definitionRegistry;
    }
}
