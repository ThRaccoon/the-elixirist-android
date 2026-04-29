package com.theelixirist.f112813.database.repositories;

import com.theelixirist.f112813.database.daos.GeneratorDao;
import com.theelixirist.f112813.database.dtos.GeneratorDto;
import com.theelixirist.f112813.database.mappers.GeneratorMapper;

import java.util.List;

public class GeneratorRepository {
    private final GeneratorDao dao;

    public GeneratorRepository(GeneratorDao dao) {
        this.dao = dao;
    }

    public void create(GeneratorDto dto) {
        dao.create(GeneratorMapper.toEntity(dto));
    }

    public List<GeneratorDto> readAll() {
        return GeneratorMapper.toDtoList(dao.readAll());
    }

    public void updateCurrentCountById(int id, int newCount) {
        dao.updateCurrentCountById(id, newCount);
    }
}
