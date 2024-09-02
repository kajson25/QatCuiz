package com.example.mobilneprojekat_1.user.user_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Log
import com.example.mobilneprojekat_1.user.UserRepository
import com.example.mobilneprojekat_1.user.user_edit.ProfileEditContract.ProfileEditState
import com.example.mobilneprojekat_1.user.user_edit.ProfileEditContract.ProfileEditEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.*

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel(){

    private val _state = MutableStateFlow(ProfileEditState())
    val state = _state.asStateFlow()
    private fun setState(reducer: ProfileEditState.() -> ProfileEditState) = _state.update(reducer)

    private val events = MutableSharedFlow<ProfileEditEvent>()

    fun setEvent(event: ProfileEditEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {
        observeEvents()
        fetchProfileInfo()
    }

    private fun observeEvents() {
        viewModelScope.launch {
            events.collect {
                when(it) {
                    is ProfileEditEvent.UpdateProfile -> {
                        repository.updateUser(it.asUserData())
                        Log.d("Kaja", "updateProfileInfo event")
                    }
                }
            }
        }
    }

    private fun fetchProfileInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val userData = repository.getUserData()
                setState {
                    copy(
                        userData = userData,
                        fetchingData = false
                    )
                }
            }
        }
    }
}