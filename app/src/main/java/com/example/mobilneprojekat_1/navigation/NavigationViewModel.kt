package com.example.mobilneprojekat_1.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilneprojekat_1.user.datastore.UserStore
import com.example.mobilneprojekat_1.navigation.NavigationContract.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor (
    private val store: UserStore
): ViewModel() {

    private val _state = MutableStateFlow(NavigationState())
    val state = _state.asStateFlow()

    private fun setState(reducer: NavigationState.() -> NavigationState) = _state.update(reducer)

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch {
            val hasAccount = withContext(Dispatchers.IO) { store.isUserRegistered() }
            setState { copy(
                hasAccount = hasAccount,
                isLoading = false
            ) }
        }
    }
}
