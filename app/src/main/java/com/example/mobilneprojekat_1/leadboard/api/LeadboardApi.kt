package com.example.mobilneprojekat_1.leadboard.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LeadboardApi {

    @GET("leaderboard")
    suspend fun getLeaderboard(
        @Query("category") categoryId: Int,
    ): List<LeaderboardApiModel>

    @POST("leaderboard")
    suspend fun postLeaderboard(
        @Body leaderboard: SubmitResultRequest
    ): SubmitResultResponse
}