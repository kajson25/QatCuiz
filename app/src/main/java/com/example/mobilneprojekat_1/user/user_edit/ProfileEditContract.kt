package com.example.mobilneprojekat_1.user.user_edit

import com.example.mobilneprojekat_1.user.datastore.UserData

interface ProfileEditContract {

    data class ProfileEditState(
        var fetchingData: Boolean = true,
        val userData: UserData = UserData(),
    )

    sealed class ProfileEditEvent {
        data class UpdateProfile(
            val name: String,
            val username: String,
            val email: String
        ) : ProfileEditEvent() {

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