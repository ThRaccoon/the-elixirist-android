package com.theelixirist.f112813.domain.mappers;

import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.domain.models.Effect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EffectDomainMapper {
    private EffectDomainMapper() {
    }

    public static Effect toDomain(EffectDto dto) {
        return new Effect(
                dto.id,
                dto.durationMs
        );
    }

    public static EffectDto toDto(Effect effect) {
        EffectDto dto = new EffectDto();
        dto.id = effect.getId();
        dto.durationMs = effect.getDurationMs();
        return dto;
    }

    public static List<EffectDto> toDtos(Collection<Effect> effects) {
        List<EffectDto> dtos = new ArrayList<>();
        for (Effect effect : effects) {
            dtos.add(toDto(effect));
        }
        return dtos;
    }
}
