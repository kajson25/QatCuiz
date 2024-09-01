package com.example.mobilneprojekat_1.cat.cats_list

import com.example.mobilneprojekat_1.database.entities.CatDbModel

data class CatListState(
    val fetching: Boolean = false,
    val error: CatListError? = null,
    val filter: String = "",
    val catsAll: List<CatDbModel> = emptyList(),

    val searchActive: Boolean = false,
    val catsFiltered: List<CatDbModel> = emptyList(),
) {
    sealed class CatListError {
        data class CatListUpdateFailed(val cause: Throwable? = null) : CatListError()
    }
}