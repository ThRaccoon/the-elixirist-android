package com.theelixirist.f112813.game.managers.save;

import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.repositories.CatalystRepository;
import com.theelixirist.f112813.game.runtime.Catalyst;
import com.theelixirist.f112813.game.state.ActiveCatalysts;

import java.util.HashMap;

public class CatalystSaveManager {
    private final CatalystRepository catalystRepository;

    public CatalystSaveManager(CatalystRepository catalystRepository) {
        this.catalystRepository = catalystRepository;
    }

    public ActiveCatalysts load() {
        HashMap<Integer, Catalyst> catalysts = new HashMap<>();
        for (CatalystDto dto : catalystRepository.readAll()) {
            catalysts.put(dto.id, new Catalyst(
                    dto.id,
                    dto.despawnDurationMs,
                    dto.normalizedPosX,
                    dto.normalizedPosY
            ));
        }

        return new ActiveCatalysts(catalysts);
    }

    public void save(ActiveCatalysts activeCatalysts)
}
