package com.theelixirist.f112813.managers;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.theelixirist.f112813.R;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static volatile AudioManager instance;

    private final SoundPool soundPool;
    private final Map<String, Integer> sounds = new HashMap<>();

    private AudioManager(Context context) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        // Load sounds
        load(context, "brew", R.raw.sfx_brew);
        load(context, "tab_switch", R.raw.sfx_tab_switch);
    }

    public static AudioManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AudioManager.class) {
                if (instance == null) {
                    instance = new AudioManager(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private void load(Context context, String key, int resId) {
        sounds.put(key, soundPool.load(context, resId, 1));
    }

    public void play(String key, float volume) {
        Integer soundId = sounds.get(key);
        if (soundId != null) {
            soundPool.play(soundId, volume, volume, 1, 0, 1f);
        }
    }
}
