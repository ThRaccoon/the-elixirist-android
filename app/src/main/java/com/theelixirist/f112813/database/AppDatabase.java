package com.theelixirist.f112813.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.theelixirist.f112813.database.daos.GeneratorDao;
import com.theelixirist.f112813.database.entities.GeneratorEntity;

@Database(entities = {GeneratorEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GeneratorDao generatorDao();
}
