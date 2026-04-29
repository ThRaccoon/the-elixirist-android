package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.database.entities.GeneratorEntity;
import com.theelixirist.f112813.game.runtime.Generator;

import java.util.ArrayList;
import java.util.List;

public class GeneratorMapper {
    private GeneratorMapper() {
    }

    public static GeneratorEntity toEntity(GeneratorDto dto) {
        GeneratorEntity entity = new GeneratorEntity();
        entity.id = dto.id;
        entity.currentCount = dto.currentCount;
        return entity;
    }

    public static GeneratorDto toDto(GeneratorEntity entity) {
        GeneratorDto dto = new GeneratorDto();
        dto.id = entity.id;
        dto.currentCount = entity.currentCount;
        return dto;
    }

    public static List<GeneratorDto> toDtoList(List<GeneratorEntity> entities) {
        List<GeneratorDto> dtos = new ArrayList<>();
        for (GeneratorEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static GeneratorDto fromRuntime(Generator generator) {
        GeneratorDto dto = new GeneratorDto();
        dto.id = generator.getId();
        dto.currentCount = generator.getCurrentCount();
        return dto;
    }

    public static Generator toRuntime(GeneratorDto dto) {
        return new Generator(
                dto.id,
                dto.currentCount
        );
    }
}
