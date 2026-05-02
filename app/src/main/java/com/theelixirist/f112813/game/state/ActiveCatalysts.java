package com.theelixirist.f112813.game.state;

import com.theelixirist.f112813.game.runtime.Catalyst;

import java.util.HashMap;

public class ActiveCatalysts {
    private HashMap<Integer, Catalyst> catalysts = new HashMap<>();

    public ActiveCatalysts(
            HashMap<Integer, Catalyst> catalysts
    ) {
        this.catalysts = catalysts;
    }

    public HashMap<Integer, Catalyst> getCatalysts() {
        return catalysts;
    }

    public Catalyst getCatalyst(int id) {
        return catalysts.get(id);
    }

    public void putCatalyst(Catalyst catalyst) {
        catalysts.put(catalyst.getId(), catalyst);
    }
}
