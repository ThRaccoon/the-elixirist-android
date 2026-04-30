package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.database.entities.ChronicleEntity;
import com.theelixirist.f112813.game.Chronicle;
import com.theelixirist.f112813.game.GameState;

public class ChronicleMapper {
    private ChronicleMapper() {
    }

    public static ChronicleEntity toEntity(ChronicleDto dto) {
        ChronicleEntity entity = new ChronicleEntity();
        entity.id = dto.id;
        entity.firstPlayedAt = dto.firstPlayedAt;
        entity.lastPlayedAt = dto.lastPlayedAt;
        entity.currentElixirs = dto.currentElixirs;
        entity.totalElixirsBrewed = dto.totalElixirsBrewed;
        entity.totalCatalystsCollected = dto.totalCatalystsCollected;
        entity.totalBuffsTriggered = dto.totalBuffsTriggered;
        return entity;
    }

    public static ChronicleDto toDto(ChronicleEntity entity) {
        ChronicleDto dto = new ChronicleDto();
        dto.id = entity.id;
        dto.firstPlayedAt = entity.firstPlayedAt;
        dto.lastPlayedAt = entity.lastPlayedAt;
        dto.currentElixirs = entity.currentElixirs;
        dto.totalElixirsBrewed = entity.totalElixirsBrewed;
        dto.totalCatalystsCollected = entity.totalCatalystsCollected;
        dto.totalBuffsTriggered = entity.totalBuffsTriggered;
        return dto;
    }

    public static ChronicleDto fromRuntime(Chronicle chronicle, GameState gameState) {
        ChronicleDto dto = new ChronicleDto();
        dto.id = 1;
        dto.firstPlayedAt = chronicle.getFirstPlayedAt();
        dto.lastPlayedAt = chronicle.getLastPlayedAt();
        dto.currentElixirs = gameState.getCurrentElixirs().toString();
        dto.totalElixirsBrewed = chronicle.getTotalElixirsBrewed().toString();
        dto.totalCatalystsCollected = chronicle.getTotalCatalystsCollected();
        dto.totalBuffsTriggered = chronicle.getTotalBuffsTriggered();
        return dto;
    }
}
