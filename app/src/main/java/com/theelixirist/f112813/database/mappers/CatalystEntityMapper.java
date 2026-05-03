package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.entities.CatalystEntity;

import java.util.ArrayList;
import java.util.List;

public class CatalystEntityMapper {
    private CatalystEntityMapper() {
    }

    public static CatalystEntity toEntity(CatalystDto dto) {
        CatalystEntity entity = new CatalystEntity();
        entity.id = dto.id;
        entity.despawnDurationMs = dto.despawnDurationMs;
        entity.normalizedPosX = dto.normalizedPosX;
        entity.normalizedPosY = dto.normalizedPosY;
        return entity;
    }

    public static List<CatalystEntity> toEntities(List<CatalystDto> dtos) {
        List<CatalystEntity> entities = new ArrayList<>();
        for (CatalystDto dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public static CatalystDto toDto(CatalystEntity entity) {
        CatalystDto dto = new CatalystDto();
        dto.id = entity.id;
        dto.despawnDurationMs = entity.despawnDurationMs;
        dto.normalizedPosX = entity.normalizedPosX;
        dto.normalizedPosY = entity.normalizedPosY;
        return dto;
    }

    public static List<CatalystDto> toDtos(List<CatalystEntity> entities) {
        List<CatalystDto> dtos = new ArrayList<>();
        for (CatalystEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
