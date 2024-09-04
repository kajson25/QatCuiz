package com.example.catapult.data.database.entities

import androidx.room.Entity

@Entity(
    tableName = "Leaderboard",
    primaryKeys = ["username", "result", "createdAt"]
)
data class LBItemDbModel (

    val username: String,
    val result: Double,
    val totalGamesPlayed: Int,
    val createdAt: Long
)