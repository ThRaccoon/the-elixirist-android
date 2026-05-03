package com.theelixirist.f112813.domain.mappers;

import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.domain.models.Catalyst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CatalystDomainMapper {
    private CatalystDomainMapper() {
    }

    public static Catalyst toDomain(CatalystDto dto) {
        return new Catalyst(
                dto.id,
                dto.despawnDurationMs,
                dto.normalizedPosX,
                dto.normalizedPosY
        );
    }

    public static CatalystDto toDto(Catalyst catalyst) {
        CatalystDto dto = new CatalystDto();
        dto.id = catalyst.getId();
        dto.despawnDurationMs = catalyst.getDespawnDurationMs();
        dto.normalizedPosX = catalyst.getNormalizedPosX();
        dto.normalizedPosY = catalyst.getNormalizedPosY();
        return dto;
    }

    public static List<CatalystDto> toDtos(Collection<Catalyst> catalysts) {
        List<CatalystDto> dtos = new ArrayList<>();
        for (Catalyst catalyst : catalysts) {
            dtos.add(toDto(catalyst));
        }
        return dtos;
    }
}
