package com.example.mobilneprojekat_1.user.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserData (
    val name: String = "",
    val username: String = "",
    val email: String = "",
)