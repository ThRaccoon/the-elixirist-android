package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.database.entities.GeneratorEntity;

public class GeneratorMapper {
    public static GeneratorEntity toEntity(GeneratorDto dto) {
        GeneratorEntity entity = new GeneratorEntity();
        entity.id = dto.id;
        entity.ownedCount = dto.ownedCount;
        return entity;
    }

    public static GeneratorDto toDto(GeneratorEntity entity) {
        GeneratorDto dto = new GeneratorDto();
        dto.id = entity.id;
        dto.ownedCount = entity.ownedCount;
        return dto;
    }
}
