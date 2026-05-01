package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.theelixirist.f112813.database.entities.EffectEntity;

import java.util.List;

@Dao
public interface EffectDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(EffectEntity entity);

    @Query("SELECT * FROM active_effects")
    List<EffectEntity> readAll();

    @Delete
    void delete(EffectEntity entity);
}
