package com.example.mobilneprojekat_1.cat.cats_preview

import com.example.mobilneprojekat_1.cat.cats_ui.CatUiModel

data class CatPreviewState (
    var catId: String,
    val catUiModel: CatUiModel? = null,
    val error: DetailsError? = null,
){
    sealed class DetailsError {
        data class DataUpdateFailed(val cause: Throwable? = null): DetailsError()
    }
}