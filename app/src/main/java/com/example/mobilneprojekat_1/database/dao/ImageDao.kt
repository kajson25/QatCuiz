package com.example.mobilneprojekat_1.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobilneprojekat_1.database.entities.ImageDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageDbModel: ImageDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ImageDbModel>)

    @Query("SELECT * FROM Images WHERE catId = :catId")
    suspend fun getAllForCat(catId: String): List<ImageDbModel>

    @Query("SELECT * FROM Images WHERE catId = :catId")
    fun fetchAllForCat(catId: String): Flow<List<ImageDbModel>>

    // In ImageDao.kt
    @Query("SELECT COUNT(*) FROM Images WHERE catId = :catId")
    suspend fun countImagesForCat(catId: String): Int
}