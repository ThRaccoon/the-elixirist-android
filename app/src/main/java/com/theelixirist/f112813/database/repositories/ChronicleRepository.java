package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.ChronicleDao;
import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.database.entities.ChronicleEntity;
import com.theelixirist.f112813.database.mappers.ChronicleEntityMapper;

public class ChronicleRepository {
    private final ChronicleDao dao;

    public ChronicleRepository(ChronicleDao dao) {
        this.dao = dao;
    }

    public ChronicleDto read() {
        ChronicleEntity entity = dao.read();
        if (entity == null) return null;
        return ChronicleEntityMapper.toDto(entity);
    }

    public void upsert(ChronicleDto dto) {
        dao.upsert(ChronicleEntityMapper.toEntity(dto));
    }
}
