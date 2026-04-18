package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.theelixirist.f112813.database.entities.GeneratorEntity;

import java.util.List;

@Dao
public interface GeneratorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(GeneratorEntity generatorEntity);

    @Query("SELECT * FROM owned_generators WHERE id = :id")
    GeneratorEntity readById(int id);

    @Query("SELECT * FROM owned_generators")
    List<GeneratorEntity> readAll();

    @Query("UPDATE owned_generators SET count = :newCount WHERE id = :id")
    void updateCountById(int id, int newCount);

    @Query("DELETE FROM owned_generators WHERE id = :id")
    void deleteById(int id);
}
