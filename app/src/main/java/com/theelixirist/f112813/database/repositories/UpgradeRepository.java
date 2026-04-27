package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.UpgradeDao;

import java.util.List;

public class UpgradeRepository {
    private final UpgradeDao dao;

    public UpgradeRepository(UpgradeDao dao) {
        this.dao = dao;
    }

    public void create(UpgradeDto dto) {
        dao.create(UpgradeMapper.toEntity(dto));
    }

    public List<UpgradeDto> getAll() {
        return UpgradeMapper.toDtoList(dao.readAll());
    }
}
