package com.theelixirist.f112813.game.managers.save;

import com.theelixirist.f112813.database.repositories.EffectRepository;

public class EffectSaveManager {
    private final EffectRepository effectRepository;

    public EffectSaveManager(EffectRepository effectRepository) {
        this.effectRepository = effectRepository;
    }
}
