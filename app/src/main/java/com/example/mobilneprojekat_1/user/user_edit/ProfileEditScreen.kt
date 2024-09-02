package com.example.mobilneprojekat_1.user.user_edit

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.profileEditScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val profileEditViewModel = hiltViewModel<ProfileEditViewModel>()
    val state by profileEditViewModel.state.collectAsState()

//    ProfileEditScreen(
//        state = state,
//        onBack = { navController.popBackStack() },
//        onSave = { firstName, lastName, nickname, email ->
//            profileEditViewModel.setEvent(ProfileEditEvent.UpdateProfile(firstName, lastName, nickname, email))
//            navController.navigate("profile")
//        }
//    )
}
