package com.example.mobilneprojekat_1.leadboard

import com.example.catapult.data.database.entities.LBItemDbModel
import com.example.mobilneprojekat_1.database.AppDatabase
import com.example.mobilneprojekat_1.leadboard.api.LeadboardApi
import com.example.mobilneprojekat_1.leadboard.api.SubmitResultRequest
import com.example.mobilneprojekat_1.user.datastore.UserStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(
    private val leaderboardApi: LeadboardApi,
    private val database: AppDatabase,
    private val store: UserStore
){

    suspend fun fetchLeaderboard(categoryId: Int = 1) {
        withContext(Dispatchers.IO) {
            val lbItems = leaderboardApi.getLeaderboard(categoryId)
            database.leaderboardDao().deleteAll()
            database.leaderboardDao().insertAll(lbItems.map { it.asLBItemDbModel(lbItems) })
        }
    }

    /**
     * We should submit the result to the API.
     * And change the last entry in the leaderboard table (published -> true)
     */
    suspend fun submitQuizResult(categoryId: Int = 1, result: Double) {
        withContext(Dispatchers.IO) {
            leaderboardApi.postLeaderboard(
                SubmitResultRequest(
                    nickname = store.getUserData().nickname,
                    result = result,
                    category = categoryId
                )
            )
        }

        // Retrieve the last entry in the leaderboard table
        val lastEntry = database.resultDao().getLastEntry()

        lastEntry.let {
            it.published = true
            database.resultDao().update(it)
        }
    }

    /** Returns Flow which holds all leaderboard items.
     * The items are sorted by result in descending order.
     * Does not have a categoryId since this app will support only 1 category of the quiz.
     */
    fun observeLeaderboard(): Flow<List<LBItemDbModel>> {
        return database.leaderboardDao().observeAll()
    }
}