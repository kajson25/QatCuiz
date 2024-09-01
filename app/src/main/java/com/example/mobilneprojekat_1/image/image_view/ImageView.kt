package com.example.mobilneprojekat_1.image.image_view

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.imageView(
    route: String,
    navController: NavController,
    arguments: List<NamedNavArgument>,
    onBack: () -> Unit,
) = composable(route = route, arguments = arguments) { backStackEntry ->

    val catId = backStackEntry.arguments?.getString("breedId") ?: ""

    val catImagesViewModel = hiltViewModel<ImageViewModel>(backStackEntry)
    val state by catImagesViewModel.state.collectAsState()

    val onImageClick: (String) -> Unit = { imageId ->
        navController.navigate("breed/images/${catId}?currentImage=$imageId")
    }

//    ImageView( // funkcija koju definisemo dole
//        state = state,
//        onImageClick = onImageClick,
//        onBack = onBack
//    )
}