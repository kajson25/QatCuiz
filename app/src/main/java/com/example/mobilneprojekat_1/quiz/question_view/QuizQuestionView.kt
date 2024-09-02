package com.example.mobilneprojekat_1.quiz.question_view

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.quizQuestionScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val quizQuestionViewModel = hiltViewModel<QuizQuestionViewModel>()
    val state by quizQuestionViewModel.state.collectAsState()

//    QuizQuestionScreen(
//        state = state,
//        onNextQuestion = {
//                answer -> quizQuestionViewModel.setEvent(QuizQuestionEvent.NextQuestion(answer))
//        },
//        publishResult = {
//                score -> quizQuestionViewModel.setEvent(QuizQuestionEvent.SubmitResult(score))
//            navController.navigate("leaderboard")
//        },
//        cancelQuiz = { navController.navigate("quiz/start")},
//        restartQuiz = { navController.navigate("quiz/start") },
//        discoverPage = { navController.navigate("breeds") }
//    )
}