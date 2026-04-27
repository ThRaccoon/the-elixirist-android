package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.GeneratorDao;
import com.theelixirist.f112813.database.entities.GeneratorEntity;

import java.util.List;

public class GeneratorRepository {
    private final GeneratorDao dao;

    public GeneratorRepository(GeneratorDao dao) {
        this.dao = dao;
    }

    public void create(GeneratorDto dto) {
        dao.create(GeneratorMapper.toEntity(dto));
    }

    public GeneratorDto getById(int id) {
        GeneratorEntity entity = dao.readById(id);
        if (entity == null) { return null; }

        return GeneratorMapper.toDto(entity);
    }

    public List<GeneratorDto> getAll() {
        return GeneratorMapper.toDtoList(dao.readAll());
    }

    public void incrementCount(int id) {
        GeneratorEntity entity = dao.readById(id);
        if (entity == null) { return; }

        dao.updateCountById(id, entity.ownedCount + 1);
    }
}
