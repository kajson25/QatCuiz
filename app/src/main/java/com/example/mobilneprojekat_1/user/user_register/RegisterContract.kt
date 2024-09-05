package com.example.mobilneprojekat_1.user.user_register

import com.example.mobilneprojekat_1.user.datastore.UserData

interface RegisterContract {
    sealed class RegisterEvent {
        data class Register(
            val firstName: String,
            val lastName: String,
            val nickname: String,
            val email: String
        ) : RegisterEvent() {

            fun asUserData() : UserData {
                return UserData(
                    firstName = firstName,
                    lastName = lastName,
                    nickname = nickname,
                    email = email,
                )
            }
        }

    }
}