package com.example.mobilneprojekat_1.cats_preview

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilneprojekat_1.mapper.asCatUiModel
import com.example.mobilneprojekat_1.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: CatRepository,
): ViewModel() {
    private val catId = savedStateHandle.get<String>("breedId")!!
    private val _state = MutableStateFlow(CatPreviewState(catId = catId))
    val state = _state.asStateFlow()
    private fun setState(reducer: CatPreviewState.() -> CatPreviewState) = _state.getAndUpdate(reducer)

    init {
        Log.d("Kaja", "Trenturni macka: $catId")
        fetchCatDetails()
    }

    private fun fetchCatDetails() {
        viewModelScope.launch {
            repository.fetchCatDetails(catId = catId)
                .filterNotNull()
                .collect {
                    Log.d("Kaja", "Usao u fetch $catId")
                    setState { copy(catUiModel = it.asCatUiModel()) }
                }
        }
    }

}