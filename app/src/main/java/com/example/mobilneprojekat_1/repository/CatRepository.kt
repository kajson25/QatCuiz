package com.example.mobilneprojekat_1.repository

import com.example.mobilneprojekat_1.networking.CatApiModel
import com.example.mobilneprojekat_1.networking.Client
import com.example.mobilneprojekat_1.networking.controller.MyApiService
import com.example.mobilneprojekat_1.cats.domain.Cat
import kotlinx.coroutines.flow.MutableStateFlow

/** Single source of truth
 *  for the whole application
 */
object CatRepository {

    private val catsApi : MyApiService = Client.retrofit.create(MyApiService::class.java)
    private var cats = MutableStateFlow(listOf<Cat>())

    // ova funkcija poziva API
    suspend fun fetchCatBreeds(): List<CatApiModel> {
        return catsApi.getListOfBreeds()
    }

    suspend fun fetchCatDetails(catId: String): List<CatApiModel> {
        return catsApi.
    }

    fun setCats(cats: List<Cat>) {
        this.cats.value = cats
    }
}