package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.theelixirist.f112813.database.entities.CatalystEntity;

import java.util.List;

@Dao
public interface CatalystDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(CatalystEntity entity);

    @Query("SELECT * FROM catalysts WHERE id = :id")
    CatalystEntity readById(int id);

    @Query("SELECT * FROM catalysts")
    List<CatalystEntity> readAll();

    @Update
    void update(CatalystEntity entity);

    @Delete
    void delete(CatalystEntity entity);
}
