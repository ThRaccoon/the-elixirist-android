package com.theelixirist.f112813.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.theelixirist.f112813.database.entities.ChronicleEntity;

@Dao
public interface ChronicleDao {
    @Query("SELECT * FROM chronicle WHERE id = 1")
    ChronicleEntity read();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(ChronicleEntity entity);
}
