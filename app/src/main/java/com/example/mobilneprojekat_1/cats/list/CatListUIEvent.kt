package com.example.mobilneprojekat_1.cats.list

sealed class CatListUIEvent {

    // when the 'filter' text changes
    data class SearchChanged(val text: String) : CatListUIEvent()

    // when we want to start the search
    data object StartSearch : CatListUIEvent()

    // when we want to end the search (exit search mode)
    data object StopSearch : CatListUIEvent()

    // when we want to clear the 'filter' string
    data object DeleteSearch : CatListUIEvent()
}