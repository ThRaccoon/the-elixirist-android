package com.theelixirist.f112813.game.managers;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.theelixirist.f112813.R;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private final SoundPool soundPool;
    private final Map<String, Integer> sounds = new HashMap<>();

    public AudioManager(Context context) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        sounds.put("brew", soundPool.load(context, R.raw.sfx_brew, 1));
        sounds.put("tab_switch", soundPool.load(context, R.raw.sfx_tab_switch, 1));
    }

    public void play(String key, float volume) {
        Integer soundId = sounds.get(key);
        if (soundId != null) {
            soundPool.play(soundId, volume, volume, 1, 0, 1f);
        }
    }
}
