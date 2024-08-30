package com.example.mobilneprojekat_1.cats_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilneprojekat_1.networking.CatApiModel
import com.example.mobilneprojekat_1.cats.domain.Cat
import com.example.mobilneprojekat_1.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatListViewModel (
    private val repository : CatRepository = CatRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CatListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatListState.() -> CatListState) = _state.getAndUpdate(reducer)

    private val events = MutableSharedFlow<CatListUIEvent>()
    fun setEvent(event : CatListUIEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {
        observeEvents()
        fetchCatBreeds()
        //loadCats()
    }

    private fun observeEvents() {
        viewModelScope.launch {
            events.collect {
                when (it) {
                    is CatListUIEvent.SearchChanged -> {
                        setState { copy(catsFiltered = emptyList())}

                        val filteredBreeds = state.value.catsAll.filter { cat -> cat.name.contains(it.text, ignoreCase = true) }
                        setState { copy(catsFiltered = filteredBreeds) }
                        setState { copy(filter = it.text) }
                    }

                    CatListUIEvent.DeleteSearch -> {
                        setState { copy(filter = "") }
                        setState { copy(catsFiltered = catsAll) }
                    }
                    CatListUIEvent.StartSearch -> {
                        setState { copy(searchActive = true) }
                        setState { copy(catsFiltered = catsAll) }
                    }
                    CatListUIEvent.StopSearch -> {
                        setState { copy(searchActive = false) }
                        setState { copy(filter = "") }
                        setState { copy(catsFiltered = emptyList()) }
                    }
                }
            }
        }
    }

    private fun fetchCatBreeds(){
        viewModelScope.launch {
            setState { copy(fetching = true) }
            try {
                withContext(Dispatchers.IO) {
                    val cats = repository.fetchCatBreeds().map { it.asCat() }
                    setState { copy(catsAll = cats) }
                    repository.setCats(cats)

                    Log.d("KAJA", "Cats fetched, ${cats.size}")
                }

            } catch (e : Error) {
                setState { copy(error = CatListState.CatListError.CatListUpdateFailed()) }
            } finally {
                setState { copy(fetching = false) }
            }
        }

    }

    private fun CatApiModel.asCat() = Cat (
        id = id?: "",
        name = name?: "",
        weight = listOf(weight?.imperial ?: "", weight?.metric ?: "").toString(),
        life_span = life_span?: "",
        bred_for = bred_for?: "",
        breed_group = breed_group?: "",
    )


}

//    private fun loadCats() {
//        viewModelScope.launch {
//            // Simulate loading cats from a repository
//            _cats.value = listOf(
//                Cat("1", "Macak", "Mala slatkica mackica", ""),
//                Cat("2", "Cizmica", "Mackica sa cizmicama", ""),
//                Cat("3", "Ljubavica", "Srculence malo slatko", "")
//            )
//        }
//    }


//    fun filterCats(query: String): List<Cat> {
//        return if (query.isEmpty()) {
//            _state.value
//        } else {
//            _state.value.filter { it.name.contains(query, ignoreCase = true) }
//        }
//    }