package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.theelixirist.f112813.database.entities.EffectEntity;
import com.theelixirist.f112813.domain.models.Effect;

import java.util.List;

@Dao
public interface EffectDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(EffectEntity entity);

    @Query("SELECT * FROM effects where id = :id")
    Effect readById(int id);

    @Query("SELECT * FROM effects")
    List<EffectEntity> readAll();

    @Update
    void update(EffectEntity entity);

    @Delete
    void delete(EffectEntity entity);
}
