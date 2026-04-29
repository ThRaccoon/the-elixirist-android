package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.entities.CatalystEntity;
import com.theelixirist.f112813.game.runtime.Catalyst;

import java.util.ArrayList;
import java.util.List;

public class CatalystMapper {
    private CatalystMapper() {
    }

    public static CatalystEntity toEntity(CatalystDto dto) {
        CatalystEntity entity = new CatalystEntity();
        entity.id = dto.id;
        entity.despawnDurationMs = dto.despawnDurationMs;
        entity.normalizedPosX = dto.normalizedPosX;
        entity.normalizedPosY = dto.normalizedPosY;
        return entity;
    }

    public static CatalystDto toDto(CatalystEntity entity) {
        CatalystDto dto = new CatalystDto();
        dto.id = entity.id;
        dto.despawnDurationMs = entity.despawnDurationMs;
        dto.normalizedPosX = entity.normalizedPosX;
        dto.normalizedPosY = entity.normalizedPosY;
        return dto;
    }

    public static List<CatalystDto> toDtoList(List<CatalystEntity> entities) {
        List<CatalystDto> dtos = new ArrayList<>();
        for (CatalystEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static CatalystDto fromRuntime(Catalyst catalyst) {
        CatalystDto dto = new CatalystDto();
        dto.id = catalyst.getId();
        dto.despawnDurationMs = catalyst.getDespawnDurationMs();
        dto.normalizedPosX = catalyst.getNormalizedPosX();
        dto.normalizedPosY = catalyst.getNormalizedPosY();
        return dto;
    }

    public static Catalyst toRuntime(CatalystDto dto) {
        return new Catalyst(
                dto.id,
                dto.despawnDurationMs,
                dto.normalizedPosX,
                dto.normalizedPosY
        );
    }
}
