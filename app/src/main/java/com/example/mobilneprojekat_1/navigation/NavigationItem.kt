package com.example.mobilneprojekat_1.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    data object Explore : NavigationItem("explore", Icons.Default.Explore, "Explore")
    data object StartQuiz : NavigationItem("quiz/start", Icons.Default.Quiz, "Start Quiz")
    data object Leaderboard : NavigationItem("leaderboard", Icons.Default.Leaderboard, "Leaderboard")
    data object Info : NavigationItem("info", Icons.Default.Info, "Info")

    companion object {
        val allItems = listOf(
            Explore,
            StartQuiz,
            Leaderboard,
            Info
        )
    }
}
