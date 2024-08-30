package com.example.mobilneprojekat_1.cats.list

import com.example.mobilneprojekat_1.cats.domain.Cat

data class CatListState (
    val fetching: Boolean = false,
    val error: CatListError? = null,
    val filter: String = "",
    val catsAll: List<Cat> = emptyList(),

    val searchActive: Boolean = false,
    val catsFiltered: List<Cat> = emptyList(),
) {
    sealed class CatListError {
        data class CatListUpdateFailed(val cause: Throwable? = null) : CatListError()
    }
}