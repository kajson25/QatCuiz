package com.example.mobilneprojekat_1.quiz.start

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobilneprojekat_1.navigation.AppDrawer

fun NavGraphBuilder.startQuizScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {
    QuizScreen(
        onStartQuizClick = { navController.navigate("quiz") },
        navController = navController,
    )
}

@Composable
fun QuizScreen(
    onStartQuizClick: () -> Unit,
    navController: NavController,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onStartQuizClick,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Start Quiz", fontSize = 20.sp)
            }
        }
    }
}