package com.theelixirist.f112813.domain.mappers;

import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.domain.models.Generator;

public class GeneratorDomainMapper {
    private GeneratorDomainMapper() {
    }

    public static Generator toDomain(GeneratorDto dto) {
        return new Generator(
                dto.id,
                dto.currentCount
        );
    }

    public static GeneratorDto toDto(Generator generator) {
        GeneratorDto dto = new GeneratorDto();
        dto.id = generator.getId();
        dto.currentCount = generator.getCurrentCount();
        return dto;
    }
}
