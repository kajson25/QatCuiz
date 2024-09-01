package com.example.mobilneprojekat_1.image.image_view

import com.example.mobilneprojekat_1.cat.cats_list.CatListState
import com.example.mobilneprojekat_1.image.image_ui.ImageUiModel

data class ImageState (
    val catId: String,
    val fetching: Boolean = false,
    val error: CatListState.CatListError? = null,

    val images: List<ImageUiModel> = emptyList()
) {
    sealed class ImageListError {
        data class ListUpdateFailed(val cause: Throwable? = null) : ImageListError()
    }
}