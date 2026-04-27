package com.theelixirist.f112813.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "spawned_catalysts")
public class CatalystEntity {
    @PrimaryKey
    public int id;

    public long despawnDurationMs;

    public float normalizedPosX;
    public float normalizedPosY;
}
