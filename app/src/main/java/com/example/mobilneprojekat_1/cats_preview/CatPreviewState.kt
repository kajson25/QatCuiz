package com.example.mobilneprojekat_1.cats_preview

import com.example.mobilneprojekat_1.cats.domain.Cat

class CatPreviewState (
    val catId: String,
    val cat: Cat? = null,
    val error: DetailsError? = null,
){
    sealed class DetailsError {
        data class DataUpdateFailed(val cause: Throwable? = null): DetailsError()
    }
}