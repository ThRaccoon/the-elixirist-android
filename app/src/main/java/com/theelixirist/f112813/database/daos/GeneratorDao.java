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
    void create(GeneratorEntity entity);

    @Query("SELECT * FROM generators")
    List<GeneratorEntity> readAll();

    @Query("UPDATE generators SET currentCount = :newCount WHERE id = :id")
    void updateCurrentCountById(int id, int newCount);
}
