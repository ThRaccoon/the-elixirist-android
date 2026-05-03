package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.entities.EffectEntity;

import java.util.ArrayList;
import java.util.List;

public class EffectEntityMapper {
    private EffectEntityMapper() {
    }

    public static EffectEntity toEntity(EffectDto dto) {
        EffectEntity entity = new EffectEntity();
        entity.id = dto.id;
        entity.durationMs = dto.durationMs;
        return entity;
    }

    public static List<EffectEntity> toEntities(List<EffectDto> dtos) {
        List<EffectEntity> entities = new ArrayList<>();
        for (EffectDto dto : dtos) {
            entities.add(EffectEntityMapper.toEntity(dto));
        }
        return entities;
    }

    public static EffectDto toDto(EffectEntity entity) {
        EffectDto dto = new EffectDto();
        dto.id = entity.id;
        dto.durationMs = entity.durationMs;
        return dto;
    }

    public static List<EffectDto> toDtos(List<EffectEntity> entities) {
        List<EffectDto> dtos = new ArrayList<>();
        for (EffectEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
