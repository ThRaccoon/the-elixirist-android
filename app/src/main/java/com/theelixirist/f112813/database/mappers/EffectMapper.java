package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.entities.EffectEntity;
import com.theelixirist.f112813.game.runtime.Effect;

import java.util.ArrayList;
import java.util.List;

public class EffectMapper {
    private EffectMapper() {
    }

    public static EffectEntity toEntity(EffectDto dto) {
        EffectEntity entity = new EffectEntity();
        entity.id = dto.id;
        entity.durationMs = dto.durationMs;
        return entity;
    }

    public static EffectDto toDto(EffectEntity entity) {
        EffectDto dto = new EffectDto();
        dto.id = entity.id;
        dto.durationMs = entity.durationMs;
        return dto;
    }

    public static List<EffectDto> toDtoList(List<EffectEntity> entities) {
        List<EffectDto> dtos = new ArrayList<>();
        for (EffectEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static EffectDto fromRuntime(Effect effect) {
        EffectDto dto = new EffectDto();
        dto.id = effect.getId();
        dto.durationMs = effect.getDurationMs();
        return dto;
    }

    public static Effect toRuntime(EffectDto dto) {
        return new Effect(
                dto.id,
                dto.durationMs
        );
    }
}
