package com.theelixirist.f112813.game.state;

import com.theelixirist.f112813.game.runtime.Effect;

import java.util.HashMap;

public class ActiveEffects {
    private final HashMap<Integer, Effect> effects = new HashMap<>();

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
