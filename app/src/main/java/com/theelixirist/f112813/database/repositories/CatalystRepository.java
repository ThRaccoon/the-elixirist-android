package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.CatalystDao;
import com.theelixirist.f112813.database.dtos.CatalystDto;
import com.theelixirist.f112813.database.entities.CatalystEntity;
import com.theelixirist.f112813.database.mappers.CatalystMapper;

import java.util.List;

public class CatalystRepository {
    private final CatalystDao dao;

    public CatalystRepository(CatalystDao dao) {
        this.dao = dao;
    }

    public void create(CatalystDto dto) {
        dao.create(CatalystMapper.toEntity(dto));
    }

    public CatalystDto getById(int id) {
        CatalystEntity entity = dao.readById(id);

        if (entity == null) {
            return null;
        }

        return CatalystMapper.toDto(entity);
    }

    public List<CatalystDto> readAll() {
        return CatalystMapper.toDtoList(dao.readAll());
    }

    public void delete(CatalystDto dto) {
        dao.delete(CatalystMapper.toEntity(dto));
    }
}
