package com.example.mobilneprojekat_1.cats_preview

import androidx.lifecycle.SavedStateHandle
import com.example.mobilneprojekat_1.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import javax.inject.Inject

@HiltViewModel
class CatPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: CatRepository,
) {
    private val catId = savedStateHandle.get<String>("catId")!!
    private val _state = MutableStateFlow(CatPreviewState(catId = catId))
    val state = _state.asStateFlow()
    private fun setState(reducer: CatPreviewState.() -> CatPreviewState) = _state.getAndUpdate(reducer)

    init {
        fetchCatDetails()
    }

    private fun fetchCatDetails() {
        viewModelScope.launch {
            repository.fetchCatBreeds()
        }
    }

}