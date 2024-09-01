package com.example.mobilneprojekat_1.user.user_register

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.registerScreen(
    route: String,
) = composable(route = route) {

    val registerViewModel = hiltViewModel<RegisterViewModel>()

//    RegisterScreen(
//        onRegister = { firstName, lastName, nickname, email ->
//            registerViewModel.setEvent(RegisterEvent.Register(firstName, lastName, nickname, email))
//        }
//    )
}