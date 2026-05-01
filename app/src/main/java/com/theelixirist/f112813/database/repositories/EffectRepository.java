package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.EffectDao;
import com.theelixirist.f112813.database.dtos.EffectDto;
import com.theelixirist.f112813.database.mappers.EffectMapper;

import java.util.List;

public class EffectRepository {
    private final EffectDao dao;

    public EffectRepository(EffectDao dao) {
        this.dao = dao;
    }

    public void create(EffectDto dto) {
        dao.create(EffectMapper.toEntity(dto));
    }

    public List<EffectDto> readAll() {
        return EffectMapper.toDtoList(dao.readAll());
    }

    public void delete(EffectDto dto) {
        dao.delete(EffectMapper.toEntity(dto));
    }
}
