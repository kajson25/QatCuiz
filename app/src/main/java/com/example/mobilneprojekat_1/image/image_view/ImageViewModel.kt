package com.example.mobilneprojekat_1.image.image_view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilneprojekat_1.cat.cats_list.CatListState
import com.example.mobilneprojekat_1.image.asImageUiModel
import com.example.mobilneprojekat_1.cat.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

@HiltViewModel
class ImageViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: CatRepository
): ViewModel() {

    private val catId = savedStateHandle.get<String>("breedId")!!

    private val _state = MutableStateFlow(ImageState(catId = catId))
    val state = _state.asStateFlow()

    private fun setState(reducer: ImageState.() -> ImageState) = _state.getAndUpdate(reducer)

    init {
        observeImages()
        fetchImages()
    }

    /**
     * Observes images for the breed from the database and updates the state.
     */
    private fun observeImages() {
        viewModelScope.launch {
            repository.fetchImagesForCat(catId)
                .distinctUntilChanged()
                .collect {
                    setState { copy(images = it.map { it.asImageUiModel() }) }
                }
        }
    }

    /**
     * Fetches images for the breed from api endpoint and saves them to the database.
     * Only if we don't have any images for the breed in the database.
     */
    private fun fetchImages() {
        viewModelScope.launch {
            setState { copy(fetching = true) }
            try {
                withContext(Dispatchers.IO) {
                    repository.saveImagesForCat(catId)
                }
            } catch (error: IOException) {
                setState { copy(error = CatListState.CatListError.CatListUpdateFailed(cause = error)) }
            } finally {
                setState { copy(fetching = false) }
            }
        }
    }

}