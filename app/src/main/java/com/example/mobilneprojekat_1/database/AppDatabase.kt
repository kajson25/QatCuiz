package com.example.mobilneprojekat_1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilneprojekat_1.database.dao.CatDao
import com.example.mobilneprojekat_1.database.dao.ImageDao
import com.example.mobilneprojekat_1.database.entities.CatDbModel
import com.example.mobilneprojekat_1.database.entities.ImageDbModel

@Database(
    entities = [
        CatDbModel :: class,
        ImageDbModel :: class,
    ],
    version = 2,
    exportSchema = true,
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun catDao(): CatDao

    abstract fun imageDao(): ImageDao   // globally stored results of all users
}