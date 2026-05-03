package com.theelixirist.f112813;

import android.app.Application;
import android.content.Context;

import com.theelixirist.f112813.game.managers.AudioManager;

public class ElixiristApp extends Application {
    private AppContainer appContainer;

    private AudioManager audioManager;

    @Override
    public void onCreate() {
        super.onCreate();

        appContainer = new AppContainer(this);

        audioManager = new AudioManager(this);
    }

    public static ElixiristApp get(Context context) {
        return (ElixiristApp) context.getApplicationContext();
    }

    public AppContainer getAppContainer() {
        return appContainer;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }
}
