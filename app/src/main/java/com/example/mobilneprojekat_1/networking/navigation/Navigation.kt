package com.example.mobilneprojekat_1.networking.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilneprojekat_1.cat.cats_list.catsListScreen
import com.example.mobilneprojekat_1.cat.cats_preview.catPreviewScreen

// renderuje screen + routing
@Composable
fun Navigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "breeds") {
        catsListScreen(
            route = "breeds",
            navController = navController
        )

        catPreviewScreen(
            route = "breed/details/{breedId}",
            arguments = listOf(
                navArgument("breedId") {
                    type = NavType.StringType
                },
//            application = context.applicationContext as Application
            ),
            navController = navController,
        )
    }
}