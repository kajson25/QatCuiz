package com.example.mobilneprojekat_1.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Images",
    foreignKeys = [
        ForeignKey(
            entity = CatDbModel::class,
            parentColumns = ["id"],
            childColumns = ["catId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ImageDbModel (

    @PrimaryKey val id: String,
    val url: String,
    val catId: String,
    val width: Int,
    val height: Int,
) {
    init {
        require(url.isNotBlank()) { "URL must not be blank" }
    }
}