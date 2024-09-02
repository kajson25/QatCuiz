package com.example.mobilneprojekat_1.user.user_profile

import com.example.mobilneprojekat_1.user.datastore.UserData

interface ProfileContract {

    data class ProfileState(
        var fetchingData: Boolean = true,

        val userData: UserData = UserData(),
//        val bestGlobalRank: Pair<Int, Double> = Pair(-1, -1.0),
//        val totalGamesPlayed: Int = 0,
//        val quizResults: List<ResultDbModel> = emptyList(),
    )
}