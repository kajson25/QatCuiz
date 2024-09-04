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

    /** Observe events sent from UI to this ViewModel */
    private fun observeEvents() {
        viewModelScope.launch {
            events.collect { event ->
                when(event) {
                    is RegisterEvent.Register -> {
                        registerUser(event) // Handle user registration
                    }
                }
            }
        }
    }

    private fun registerUser(event: RegisterEvent.Register) {
        viewModelScope.launch {
            try {
                // Call the repository to register the user
                repository.registerUser(event.asUserData())
                // Notify the UI with success event or callback
                _onRegisterSuccess() // Call UI callback or send success signal
            } catch (e: Exception) {
                // Notify the UI with error event or callback
                _onRegisterError(e.message ?: "Unknown error")
            }
        }
    }

    // Simple callback functions for success or error handling
    private var _onRegisterSuccess: () -> Unit = {}
    private var _onRegisterError: (String) -> Unit = {}

    // Functions to be set by the UI to observe success or error
    fun setOnRegisterSuccessListener(listener: () -> Unit) {
        _onRegisterSuccess = listener
    }

    fun setOnRegisterErrorListener(listener: (String) -> Unit) {
        _onRegisterError = listener
    }
}
