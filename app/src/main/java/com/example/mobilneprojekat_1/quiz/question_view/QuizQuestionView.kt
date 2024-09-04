package com.example.mobilneprojekat_1.quiz.question_view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

fun NavGraphBuilder.quizQuestionScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val quizQuestionViewModel = hiltViewModel<QuizQuestionViewModel>()
    val state by quizQuestionViewModel.state.collectAsState()

    QuizQuestionScreen(
        state = state,
        onNextQuestion = {
                answer -> quizQuestionViewModel.setEvent(QuizQuestionContract.QuizQuestionEvent.NextQuestion(answer))
        },
        publishResult = {
                score -> quizQuestionViewModel.setEvent(QuizQuestionContract.QuizQuestionEvent.SubmitResult(score))
            navController.navigate("leaderboard")
        },
        cancelQuiz = { navController.navigate("quiz/start")},
        restartQuiz = { navController.navigate("quiz/start") },
        discoverPage = { navController.navigate("breeds") }
    )
}

@Composable
fun QuizQuestionScreen(
    state: QuizQuestionContract.QuizQuestionState,
    onNextQuestion: (String) -> Unit,
    publishResult: (Double) -> Unit,
    cancelQuiz: () -> Unit,
    restartQuiz: () -> Unit,
    discoverPage: () -> Unit
) {
    if (state.creatingQuestions) {
        // Show a loading indicator while questions are being created
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.quizFinished) {
        // Show the final score and options to restart or go to the leaderboard
        QuizFinishedScreen(
            score = state.score,
            onRestartQuiz = restartQuiz,
            onDiscoverPage = discoverPage,
            onPublishResult = { publishResult(state.score) }
        )
    } else {
        // Show the current question
        val currentQuestion = state.questions[state.currentQuestionIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Timer
            Text(text = "Time left: ${state.timeLeft}s", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            // Question text
            Text(text = currentQuestion.text, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // Cat image
            currentQuestion.catImageUrl?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = "Cat Image",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color.Gray, shape = RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // List of answers (shuffled)
            currentQuestion.answers.forEach { answer ->
                Button(
                    onClick = { onNextQuestion(answer) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = answer)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cancel quiz button
            Button(onClick = cancelQuiz) {
                Text("Cancel Quiz")
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun QuizFinishedScreen(
    score: Double,
    onRestartQuiz: () -> Unit,
    onDiscoverPage: () -> Unit,
    onPublishResult: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Quiz Finished!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Your Score: ${String.format("%.2f", score)}", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRestartQuiz) {
            Text("Restart Quiz")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onPublishResult) {
            Text("Publish Score to Leaderboard")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onDiscoverPage) {
            Text("Discover More Cats")
        }
    }
}
