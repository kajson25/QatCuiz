package com.example.mobilneprojekat_1.user.user_profile

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.profileScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val state by profileViewModel.state.collectAsState()

//    ProfileScreen(
//        state = state,
//        onEditClick = { navController.navigate("profile/edit") },
//        navController = navController,
//    )
}