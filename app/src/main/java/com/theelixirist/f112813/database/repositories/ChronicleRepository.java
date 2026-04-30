package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.ChronicleDao;
import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.database.entities.ChronicleEntity;
import com.theelixirist.f112813.database.mappers.ChronicleMapper;

public class ChronicleRepository {
    private final ChronicleDao dao;

    public ChronicleRepository(ChronicleDao dao) {
        this.dao = dao;
    }

    public void create(ChronicleDto dto) {
        dao.create(ChronicleMapper.toEntity(dto));
    }

    public ChronicleDto read() {
        ChronicleEntity entity = dao.read();
        if (entity == null) return null;
        return ChronicleMapper.toDto(entity);
    }

    public void update(ChronicleDto dto) {
        dao.update(ChronicleMapper.toEntity(dto));
    }
}
