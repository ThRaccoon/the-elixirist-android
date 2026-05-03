package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.theelixirist.f112813.database.entities.CatalystEntity;

import java.util.List;

@Dao
public interface CatalystDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(CatalystEntity entity);

    @Query("SELECT * FROM catalysts")
    List<CatalystEntity> readAll();

    @Delete
    void delete(CatalystEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAll(List<CatalystEntity> entities);
}
