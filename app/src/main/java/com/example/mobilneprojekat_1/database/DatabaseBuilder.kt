package com.example.mobilneprojekat_1.database

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DatabaseBuilder @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun build(): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "qatcuiz.db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}