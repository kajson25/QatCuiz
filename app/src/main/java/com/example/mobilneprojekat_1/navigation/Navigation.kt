package com.example.mobilneprojekat_1.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mobilneprojekat_1.cats.list.catsListScreen
import com.example.mobilneprojekat_1.cats.preview.catPreviewScreen

// renderuje screen
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
            navController = navController,
//            application = context.applicationContext as Application
        )
    }
}