package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.theelixirist.f112813.database.entities.ChronicleEntity;

@Dao
public interface ChronicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(ChronicleEntity entity);

    @Query("SELECT * FROM chronicle WHERE id = 1")
    ChronicleEntity read();

    @Update
    void update(ChronicleEntity entity);
}
