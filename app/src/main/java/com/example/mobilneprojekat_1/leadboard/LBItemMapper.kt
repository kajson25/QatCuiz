package com.example.mobilneprojekat_1.leadboard

import com.example.catapult.data.database.entities.LBItemDbModel
import com.example.mobilneprojekat_1.leadboard.api.LeaderboardApiModel


fun LeaderboardApiModel.asLBItemDbModel(
    allResults: List<LeaderboardApiModel>
): LBItemDbModel {

    val totalGamesPlayed = allResults.count { it.nickname == this.nickname }

    // id is automatically generated
    return LBItemDbModel(
        username = nickname,
        result = result,
        totalGamesPlayed = totalGamesPlayed,
        createdAt = createdAt
    )
}

fun LBItemDbModel.asLBItemUiModel(): LBItemUiModel {
    return LBItemUiModel(
        username = username,
        result = result,
        totalGamesPlayed = totalGamesPlayed,
    )
}