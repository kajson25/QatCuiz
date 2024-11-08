package com.example.mobilneprojekat_1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilneprojekat_1.cat.cats_list.catsListScreen
import com.example.mobilneprojekat_1.cat.cats_preview.catPreviewScreen
import com.example.mobilneprojekat_1.leadboard.screen.leaderboardScreen
import com.example.mobilneprojekat_1.quiz.question_view.quizQuestionScreen
import com.example.mobilneprojekat_1.quiz.start.startQuizScreen
import com.example.mobilneprojekat_1.user.user_profile.profileScreen
import com.example.mobilneprojekat_1.user.user_register.registerScreen

// renderuje screen + routing
/**
 * TODO
 * Dodati GUI za dodatne funkcionalnosti - Edit, Welcome....
 * Prebaciti sve na Contract
 *
 */
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<NavigationViewModel>()
    val state by viewModel.state.collectAsState()

    val startDestination = when {
//        state.isLoading -> "welcome"
        state.hasAccount -> "explore"
        else -> "register"
    }

    NavHost(navController = navController, startDestination = "register") {

        registerScreen(
            route = "register",
            onRegisterSuccess = { navController.navigate("explore") },
        )

        catsListScreen(
            route = "explore",
            navController = navController,
        )

        catPreviewScreen(
            route = "breed/details/{breedId}",
            arguments = listOf(
                navArgument("breedId") {
                    type = NavType.StringType
                },
            ),
            navController = navController,
        )

        startQuizScreen(
            route = "quiz/start",
            navController = navController,
        )

        quizQuestionScreen(
            route = "quiz",
            navController = navController,
        )

        leaderboardScreen(
            route = "leaderboard",
            navController = navController,
        )

        profileScreen(
            route = "profile",
            navController = navController,
        )

//        profileEditScreen(
//            route = "profile/edit",
//            navController = navController,
//        )
    }
}

