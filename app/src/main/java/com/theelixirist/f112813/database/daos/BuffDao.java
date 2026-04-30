package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.theelixirist.f112813.database.entities.BuffEntity;

import java.util.List;

@Dao
public interface BuffDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(BuffEntity entity);

    @Query("SELECT * FROM active_buffs")
    List<BuffEntity> readAll();

    @Delete
    void delete(BuffEntity entity);
}
