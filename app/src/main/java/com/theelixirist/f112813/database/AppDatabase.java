package com.theelixirist.f112813.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.theelixirist.f112813.database.daos.EffectDao;
import com.theelixirist.f112813.database.daos.CatalystDao;
import com.theelixirist.f112813.database.daos.ChronicleDao;
import com.theelixirist.f112813.database.daos.GeneratorDao;
import com.theelixirist.f112813.database.daos.UpgradeDao;
import com.theelixirist.f112813.database.entities.EffectEntity;
import com.theelixirist.f112813.database.entities.CatalystEntity;
import com.theelixirist.f112813.database.entities.ChronicleEntity;
import com.theelixirist.f112813.database.entities.GeneratorEntity;
import com.theelixirist.f112813.database.entities.UpgradeEntity;

@Database(entities = {
        ChronicleEntity.class,
        GeneratorEntity.class,
        UpgradeEntity.class,
        CatalystEntity.class,
        EffectEntity.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ChronicleDao chronicleDao();

    public abstract GeneratorDao generatorDao();

    public abstract UpgradeDao upgradeDao();

    public abstract CatalystDao catalystDao();

    public abstract EffectDao effectDao();
}
