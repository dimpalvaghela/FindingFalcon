package com.findingfalcone.feproblem1.data.local.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.findingfalcone.feproblem1.data.local.db.dao.DummyDao
import com.findingfalcone.feproblem1.data.local.db.entity.DummyEntity
import com.findingfalcone.feproblem1.utils.Converters
import javax.inject.Singleton


@Singleton
@Database(
    entities = [
        DummyEntity::class
    ],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    version = 2,
    exportSchema = true,
)

@TypeConverters(Converters::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun dummyDao(): DummyDao

}