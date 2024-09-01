package com.example.mobilneprojekat_1.user.user_register

import com.example.mobilneprojekat_1.user.datastore.UserData

interface RegisterContract {
    sealed class RegisterEvent {
        data class Register(
            val name: String,
            val username: String,
            val email: String
        ) : RegisterEvent() {

            fun asUserData() : UserData {
                return UserData(
                    name = name,
                    username = username,
                    email = email
                )
            }
        }

    }
}