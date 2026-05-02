package com.theelixirist.f112813.domain.registries;

import com.theelixirist.f112813.domain.models.Catalyst;

import java.util.HashMap;

public class CatalystRegistry {
    private final HashMap<Integer, Catalyst> catalysts;

    public CatalystRegistry(HashMap<Integer, Catalyst> catalysts) {
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
