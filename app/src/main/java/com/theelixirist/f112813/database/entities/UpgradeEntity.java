package com.theelixirist.f112813.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "owned_upgrades")
public class UpgradeEntity {
    @PrimaryKey
    public int id;
}
