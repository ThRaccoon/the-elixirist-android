package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.CatalystDao;
import com.theelixirist.f112813.game.models.Catalyst;

import java.util.List;

public class CatalystRepository {
    private final CatalystDao dao;

    public CatalystRepository(CatalystDao dao) {
        this.dao = dao;
    }

    public void create(Catalyst catalyst) {
        dao.create(CatalystMapper.toEntity(dto));
    }

    public List<CatalystDto> getAll() {
        return CatalystMapper.toDtoList(dao.readAll());
    }

    public void delete(CatalystDto dto) {
        dao.delete(CatalystMapper.toEntity(dto));
    }
}
