package com.theelixirist.f112813.save.handlers;

import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.repositories.EffectRepository;
import com.theelixirist.f112813.domain.mappers.EffectDomainMapper;
import com.theelixirist.f112813.domain.models.Effect;
import com.theelixirist.f112813.domain.registries.EffectRegistry;
import com.theelixirist.f112813.save.Saveable;

import java.util.HashMap;
import java.util.Map;

public class EffectSaveHandler implements Saveable<Map<Integer, Effect>> {
    private final EffectRegistry effectRegistry;
    private final EffectRepository effectRepository;

    public EffectSaveHandler(EffectRegistry effectRegistry, EffectRepository effectRepository) {
        this.effectRegistry = effectRegistry;
        this.effectRepository = effectRepository;
    }

    @Override
    public Map<Integer, Effect> load() {
        HashMap<Integer, Effect> effects = new HashMap<>();
        for (EffectDto dto : effectRepository.readAll()) {
            effects.put(dto.id, new Effect(
                    dto.id,
                    dto.durationMs
            ));
        }
        return effects;
    }

    @Override
    public void save() {
        effectRepository.upsertAll(
                EffectDomainMapper.toDtos(effectRegistry.getEffects().values())
        );
    }

    public void create(Effect effect) {
        effectRepository.create(EffectDomainMapper.toDto(effect));
    }

    public void delete(Effect effect) {
        effectRepository.delete(EffectDomainMapper.toDto(effect));
    }
}
