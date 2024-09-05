package com.example.mobilneprojekat_1.user

import android.util.Log
import com.example.mobilneprojekat_1.database.AppDatabase
import com.example.mobilneprojekat_1.leadboard.LeaderboardRepository
import com.example.mobilneprojekat_1.user.datastore.UserData
import com.example.mobilneprojekat_1.user.datastore.UserStore
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val lbRepository: LeaderboardRepository,
    private val database: AppDatabase,
    private val store: UserStore
){

    suspend fun getUserData() = store.getUserData()

    suspend fun getAllResults() = database.resultDao().getAll()

    suspend fun getBestGlobalRank(): Pair<Int, Double> {
        if (database.leaderboardDao().getAll().isEmpty())
            lbRepository.fetchLeaderboard()
        val username = store.getUserData().nickname
        return database.leaderboardDao().getBestGlobalRank(username)
    }

    suspend fun registerUser(userData: UserData) {
        Log.d("Kaja", "User repository - registering user: $userData")
        store.setData(userData)
    }

    suspend fun updateUser(userData: UserData) {
        Log.d("Kaja", "User repository - updating user: $userData")
        store.updateData { userData }
    }
}