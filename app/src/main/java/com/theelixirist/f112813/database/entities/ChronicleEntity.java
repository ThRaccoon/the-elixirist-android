package com.theelixirist.f112813.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chronicle")
public class ChronicleEntity {
    @PrimaryKey
    public int id = 1;
    public long firstPlayedAt;
    public long lastPlayedAt;
    public String currentElixirs;
    public String totalElixirsBrewed;
    public long totalCatalystsCollected;
    public long totalEffectsTriggered;
}
