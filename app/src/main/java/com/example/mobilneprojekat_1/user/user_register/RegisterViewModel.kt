package com.example.mobilneprojekat_1.user.user_register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilneprojekat_1.user.UserRepository
import com.example.mobilneprojekat_1.user.user_register.RegisterContract.RegisterEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: UserRepository,
): ViewModel() {

    private val events = MutableSharedFlow<RegisterEvent>()

    fun setEvent(event: RegisterEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {
        observeEvents()
    }

    /** Observe events sent from UI to this View Model */
    private fun observeEvents() {
        viewModelScope.launch {
            events.collect {
                when(it) {
                    is RegisterEvent.Register -> {
                        repository.registerUser(it.asUserData())
                    }
                }
            }
        }
    }
}