package com.example.mobilneprojekat_1.leadboard.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilneprojekat_1.leadboard.LeaderboardRepository
import com.example.mobilneprojekat_1.leadboard.asLBItemUiModel
import com.example.mobilneprojekat_1.leadboard.screen.LeaderboardContract.LeaderboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val repository: LeaderboardRepository
): ViewModel() {
    private val _state = MutableStateFlow(LeaderboardState())
    val state = _state.asStateFlow()

    private fun setState(reducer: LeaderboardState.() -> LeaderboardState) = _state.update(reducer)

    init {
        observeLeaderboard()
        fetchLeaderboard()
    }

    private fun observeLeaderboard() {
        viewModelScope.launch {
            repository.observeLeaderboard().collect {
                setState { copy(
                    leaderboardItems = it.map { it.asLBItemUiModel() }
                ) }
            }
        }
    }

    private fun fetchLeaderboard() {
        viewModelScope.launch {
            setState { copy(fetching = true) }
            try {
                repository.fetchLeaderboard()
            }  catch (error: IOException) {
                setState { copy(error = LeaderboardState.ListError.ListUpdateFailed(cause = error)) }
            } finally {
                setState { copy(fetching = false) }
            }
            setState { copy(fetching = false) }
        }
    }
}