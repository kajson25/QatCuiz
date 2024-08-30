package com.example.mobilneprojekat_1.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobilneprojekat_1.database.entities.CatDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(catDbModel: CatDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<CatDbModel>)

    @Query("SELECT * FROM Cats")
    suspend fun getAll(): List<CatDbModel>

    suspend fun getCountOfCats(): Int {
        return getAll().size
    }

    @Query("SELECT * FROM Cats")
    fun fetchAll(): Flow<List<CatDbModel>>

    @Query("SELECT * FROM Cats WHERE id = :catId")
    fun fetchCatById(catId: String): Flow<CatDbModel?>

    suspend fun getAllTemperaments(): List<String> {
        return getAll()
            .asSequence()
            .map { it.temperament.split(",") }
            .flatten()
            .toList()
            .map { it.trim() }
            .map { it.lowercase() }
            .distinct()
    }
}