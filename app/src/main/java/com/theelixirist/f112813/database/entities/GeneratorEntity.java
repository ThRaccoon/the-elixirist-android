package com.theelixirist.f112813.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "owned_generators")
public class GeneratorEntity {
    @PrimaryKey
    public int id;
    public int count;
}
