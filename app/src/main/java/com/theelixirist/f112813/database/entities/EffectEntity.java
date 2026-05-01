package com.theelixirist.f112813.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "active_effects")
public class EffectEntity {
    @PrimaryKey
    public int id;

    public long durationMs;
}
