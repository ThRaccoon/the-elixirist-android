package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.CatalystDao;
import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.mappers.CatalystEntityMapper;

import java.util.List;

public class CatalystRepository {
    private final CatalystDao dao;

    public CatalystRepository(CatalystDao dao) {
        this.dao = dao;
    }

    public void create(CatalystDto dto) {
        dao.create(CatalystEntityMapper.toEntity(dto));
    }

    public List<CatalystDto> readAll() {
        return CatalystEntityMapper.toDtos(dao.readAll());
    }

    public void delete(CatalystDto dto) {
        dao.delete(CatalystEntityMapper.toEntity(dto));
    }

    public void upsertAll(List<CatalystDto> dtos) {
        dao.upsertAll(CatalystEntityMapper.toEntities(dtos));
    }
}
