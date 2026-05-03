package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.EffectDao;
import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.mappers.EffectEntityMapper;

import java.util.List;

public class EffectRepository {
    private final EffectDao dao;

    public EffectRepository(EffectDao dao) {
        this.dao = dao;
    }

    public void create(EffectDto dto) {
        dao.create(EffectEntityMapper.toEntity(dto));
    }

    public List<EffectDto> readAll() {
        return EffectEntityMapper.toDtos(dao.readAll());
    }

    public void delete(EffectDto dto) {
        dao.delete(EffectEntityMapper.toEntity(dto));
    }

    public void upsertAll(List<EffectDto> dtos) {
        dao.upsertAll(EffectEntityMapper.toEntities(dtos));
    }
}
