package com.theelixirist.f112813.game;

import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.database.repositories.ChronicleRepository;
import com.theelixirist.f112813.game.math.BigDouble;

public class Chronicle {
    ChronicleRepository chronicleRepository;
    GameState gameState;

    private long firstPlayedAt;
    private long lastPlayedAt;

    private BigDouble totalElixirsBrewed;
    private long totalCatalystsCollected;
    private long totalBuffsTriggered;

    public Chronicle(
            ChronicleRepository chronicleRepository,
            GameState gameState
    ) {
        this.chronicleRepository = chronicleRepository;
        this.gameState = gameState;
    }

    public long getFirstPlayedAt() {
        return firstPlayedAt;
    }

    public long getLastPlayedAt() {
        return lastPlayedAt;
    }

    public BigDouble getTotalElixirsBrewed() {
        return totalElixirsBrewed;
    }

    public long getTotalCatalystsCollected() {
        return totalCatalystsCollected;
    }

    public long getTotalBuffsTriggered() {
        return totalBuffsTriggered;
    }

    public void addBrewedElixirs(BigDouble amount) {
        totalElixirsBrewed.add(amount);
    }

    public void incrementTotalCatalystsCollected() {
        totalCatalystsCollected++;
    }

    public void incrementTotalBuffsTriggered() {
        totalBuffsTriggered++;
    }

    public void loadFromDatabase() {
        ChronicleDto dto = chronicleRepository.read();

        if (dto == null) {
            firstPlayedAt = System.currentTimeMillis();
            lastPlayedAt = firstPlayedAt;
            totalElixirsBrewed = new BigDouble(0, 0);
            totalCatalystsCollected = 0;
            totalBuffsTriggered = 0;

            gameState.setCurrentElixirs(new BigDouble(0, 0));
        } else {
            firstPlayedAt = dto.firstPlayedAt;
            lastPlayedAt = dto.lastPlayedAt;
            totalElixirsBrewed = new BigDouble(dto.totalElixirsBrewed);
            totalCatalystsCollected = dto.totalCatalystsCollected;
            totalBuffsTriggered = dto.totalBuffsTriggered;

            gameState.setCurrentElixirs(new BigDouble(dto.currentElixirs));
        }
    }
}
