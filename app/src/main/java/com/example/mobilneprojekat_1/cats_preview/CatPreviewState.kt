package com.example.mobilneprojekat_1.cats_preview

import com.example.mobilneprojekat_1.cats.ui_models.CatUiModel

data class CatPreviewState (
    val catId: String,
    val catUiModel: CatUiModel? = null,
    val error: DetailsError? = null,
){
    sealed class DetailsError {
        data class DataUpdateFailed(val cause: Throwable? = null): DetailsError()
    }
}