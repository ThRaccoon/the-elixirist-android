package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.BuffDao;
import com.theelixirist.f112813.database.dtos.BuffDto;
import com.theelixirist.f112813.database.mappers.BuffMapper;

import java.util.List;

public class BuffRepository {
    private final BuffDao dao;

    public BuffRepository(BuffDao dao) {
        this.dao = dao;
    }

    public void create(BuffDto dto) {
        dao.create(BuffMapper.toEntity(dto));
    }

    public List<BuffDto> readAll() {
        return BuffMapper.toDtoList(dao.readAll());
    }

    public void delete(BuffDto dto) {
        dao.delete(BuffMapper.toEntity(dto));
    }
}
