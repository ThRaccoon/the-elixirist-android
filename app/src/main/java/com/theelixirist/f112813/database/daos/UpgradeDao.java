package com.theelixirist.f112813.database.daos;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.theelixirist.f112813.database.entities.UpgradeEntity;

import java.util.List;

public interface UpgradeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void create(UpgradeEntity entity);

    @Query("SELECT * FROM active_upgrades")
    List<UpgradeEntity> readAll();
}
