package com.example.mobilneprojekat_1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catapult.data.database.entities.LBItemDbModel
import com.example.catapult.data.database.entities.ResultDbModel
import com.example.mobilneprojekat_1.database.dao.CatDao
import com.example.mobilneprojekat_1.database.dao.ImageDao
import com.example.mobilneprojekat_1.database.dao.LBItemDao
import com.example.mobilneprojekat_1.database.dao.ResultDao
import com.example.mobilneprojekat_1.database.entities.CatDbModel
import com.example.mobilneprojekat_1.database.entities.ImageDbModel

@Database(
    entities = [
        CatDbModel :: class,
        ImageDbModel :: class,
        ResultDbModel :: class,
        LBItemDbModel :: class
    ],
    version = 3,
    exportSchema = true,
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun catDao(): CatDao

    abstract fun imageDao(): ImageDao   // globally stored results of all users

    abstract fun resultDao(): ResultDao     // locally stored results of user on this device

    abstract fun leaderboardDao(): LBItemDao    // globally stored results of all users

}