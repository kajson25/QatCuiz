package com.example.mobilneprojekat_1.navigation

interface NavigationContract {

    data class NavigationState(
        val hasAccount: Boolean = false,
        val isLoading: Boolean = true,
    )
}