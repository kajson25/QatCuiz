package com.example.mobilneprojekat_1.leadboard.screen

import com.example.mobilneprojekat_1.leadboard.LBItemUiModel

interface LeaderboardContract {

    data class LeaderboardState(
        val leaderboardItems: List<LBItemUiModel> = emptyList(),
        val fetching: Boolean = false,
        val error: ListError? = null,
    ) {
        sealed class ListError {
            data class ListUpdateFailed(val cause: Throwable? = null) : ListError()
        }
    }
}