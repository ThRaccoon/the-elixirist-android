package com.theelixirist.f112813.save.handlers;

import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.repositories.EffectRepository;
import com.theelixirist.f112813.domain.mappers.EffectDomainMapper;
import com.theelixirist.f112813.domain.models.Effect;
import com.theelixirist.f112813.domain.registries.EffectRegistry;
import com.theelixirist.f112813.save.Saveable;

import java.util.HashMap;

public class EffectSaveHandler implements Saveable<HashMap<Integer, Effect>> {
    private EffectRegistry effectRegistry;
    private final EffectRepository effectRepository;

    public EffectSaveHandler(EffectRepository effectRepository) {
        this.effectRepository = effectRepository;
    }

    public void setEffectRegistry(EffectRegistry effectRegistry) {
        this.effectRegistry = effectRegistry;
    }

    @Override
    public HashMap<Integer, Effect> load() {
        HashMap<Integer, Effect> effects = new HashMap<>();
        for (EffectDto dto : effectRepository.readAll()) {
            effects.put(dto.id, EffectDomainMapper.toDomain(dto));
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
