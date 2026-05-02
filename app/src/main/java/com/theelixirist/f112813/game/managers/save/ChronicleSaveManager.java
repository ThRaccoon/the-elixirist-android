package com.theelixirist.f112813.game.managers.save;

import com.theelixirist.f112813.database.repositories.ChronicleRepository;

public class ChronicleSaveManager {
    private final ChronicleRepository chronicleRepository;

    public ChronicleSaveManager(ChronicleRepository chronicleRepository) {
        this.chronicleRepository = chronicleRepository;
    }
}
