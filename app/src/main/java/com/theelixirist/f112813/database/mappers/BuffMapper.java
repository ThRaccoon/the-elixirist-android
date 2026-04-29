package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.BuffDto;
import com.theelixirist.f112813.database.entities.BuffEntity;
import com.theelixirist.f112813.game.runtime.Buff;

import java.util.ArrayList;
import java.util.List;

public class BuffMapper {
    private BuffMapper() {
    }

    public static BuffEntity toEntity(BuffDto dto) {
        BuffEntity entity = new BuffEntity();
        entity.id = dto.id;
        entity.durationMs = dto.durationMs;
        return entity;
    }

    public static BuffDto toDto(BuffEntity entity) {
        BuffDto dto = new BuffDto();
        dto.id = entity.id;
        dto.durationMs = entity.durationMs;
        return dto;
    }

    public static List<BuffDto> toDtoList(List<BuffEntity> entities) {
        List<BuffDto> dtos = new ArrayList<>();
        for (BuffEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static BuffDto fromRuntime(Buff buff) {
        BuffDto dto = new BuffDto();
        dto.id = buff.getId();
        dto.durationMs = buff.getDurationMs();
        return dto;
    }

    public static Buff toRuntime(BuffDto dto) {
        return new Buff(
                dto.id,
                dto.durationMs
        );
    }
}
