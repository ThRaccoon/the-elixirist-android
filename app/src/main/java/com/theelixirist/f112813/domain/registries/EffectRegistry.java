package com.theelixirist.f112813.domain.registries;

import com.theelixirist.f112813.domain.models.Effect;

import java.util.HashMap;

public class EffectRegistry {
    private final HashMap<Integer, Effect> effects;

    public EffectRegistry(HashMap<Integer, Effect> effects) {
        this.effects = effects;
    }

    public HashMap<Integer, Effect> getEffects() {
        return effects;
    }

    public Effect getEffect(int id) {
        return effects.get(id);
    }

    public void putEffect(Effect effect) {
        effects.put(effect.getId(), effect);
    }
}
