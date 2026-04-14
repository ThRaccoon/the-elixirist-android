package com.theelixirist.f112813;

import android.app.Application;
import android.content.Context;

import com.theelixirist.f112813.managers.AudioManager;

public class ElixiristApp extends Application {
    private AudioManager audioManager;

    @Override
    public void onCreate() {
        super.onCreate();

        audioManager = AudioManager.getInstance(this);
    }

    public static ElixiristApp get(Context context) {
        return (ElixiristApp) context.getApplicationContext();
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }
}
