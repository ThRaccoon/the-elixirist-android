package com.theelixirist.f112813.domain.mappers;

import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.domain.models.Chronicle;
import com.theelixirist.f112813.game.math.BigDouble;

public class ChronicleDomainMapper {
    private ChronicleDomainMapper() {
    }

    public static Chronicle toDomain(ChronicleDto dto) {
        return new Chronicle(
                dto.firstPlayedAt,
                dto.lastPlayedAt,
                new BigDouble(dto.currentElixirs),
                new BigDouble(dto.totalElixirsBrewed),
                dto.totalCatalystsCollected,
                dto.totalEffectsTriggered
        );
    }

    public static ChronicleDto toDto(Chronicle chronicle) {
        ChronicleDto dto = new ChronicleDto();
        dto.firstPlayedAt = chronicle.getFirstPlayedAt();
        dto.lastPlayedAt = chronicle.getLastPlayedAt();
        dto.currentElixirs = chronicle.getCurrentElixirs().toString();
        dto.totalElixirsBrewed = chronicle.getTotalElixirsBrewed().toString();
        dto.totalCatalystsCollected = chronicle.getTotalCatalystsCollected();
        dto.totalEffectsTriggered = chronicle.getTotalEffectsTriggered();
        return dto;
    }
}
