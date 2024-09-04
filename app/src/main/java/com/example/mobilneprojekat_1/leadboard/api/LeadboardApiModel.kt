package com.example.mobilneprojekat_1.leadboard.api

import kotlinx.serialization.Serializable

@Serializable
data class SubmitResultRequest(
    val username: String,
    val result: Double,
    val category: Int
)

@Serializable
data class SubmitResultResponse(
    val result: LeaderboardApiModel,
    val ranking: Int
)

@Serializable
data class LeaderboardApiModel(
    val category: Int = 0,
    val username: String = "",
    val result: Double = 0.0,
    val createdAt: Long = 0L        // timestamp
)