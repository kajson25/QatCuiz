package com.example.catapult.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// This user's results in the game

@Entity(tableName = "Results")
data class ResultDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val result: Double,
    val createdAt: Long,
    var published: Boolean = false
)