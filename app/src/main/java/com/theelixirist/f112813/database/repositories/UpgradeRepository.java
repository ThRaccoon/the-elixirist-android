package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.UpgradeDao;
import com.theelixirist.f112813.database.dtos.UpgradeDto;
import com.theelixirist.f112813.database.mappers.UpgradeEntityMapper;

import java.util.List;

public class UpgradeRepository {
    private final UpgradeDao dao;

    public UpgradeRepository(UpgradeDao dao) {
        this.dao = dao;
    }

    public void create(UpgradeDto dto) {
        dao.create(UpgradeEntityMapper.toEntity(dto));
    }

    public List<UpgradeDto> readAll() {
        return UpgradeEntityMapper.toDtos(dao.readAll());
    }
}
