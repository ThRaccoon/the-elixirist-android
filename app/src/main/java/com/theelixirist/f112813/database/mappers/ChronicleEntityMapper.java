package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.database.entities.ChronicleEntity;

public class ChronicleEntityMapper {
    private ChronicleEntityMapper() {
    }

    public static ChronicleEntity toEntity(ChronicleDto dto) {
        ChronicleEntity entity = new ChronicleEntity();
        entity.id = dto.id;
        entity.firstPlayedAt = dto.firstPlayedAt;
        entity.lastPlayedAt = dto.lastPlayedAt;
        entity.currentElixirs = dto.currentElixirs;
        entity.totalElixirsBrewed = dto.totalElixirsBrewed;
        entity.totalCatalystsCollected = dto.totalCatalystsCollected;
        entity.totalEffectsTriggered = dto.totalEffectsTriggered;
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
        dto.totalEffectsTriggered = entity.totalEffectsTriggered;
        return dto;
    }
}
