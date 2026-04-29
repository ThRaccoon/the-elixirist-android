package com.theelixirist.f112813.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "active_buffs")
public class BuffEntity {
    @PrimaryKey
    public int id;

    public long durationMs;
}
