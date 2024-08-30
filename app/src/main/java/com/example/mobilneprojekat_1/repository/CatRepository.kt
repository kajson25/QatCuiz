package com.example.mobilneprojekat_1.repository

import com.example.mobilneprojekat_1.networking.CatApiModel
import com.example.mobilneprojekat_1.networking.controller.MyApiService
import com.example.mobilneprojekat_1.cats.ui_models.CatUiModel
import com.example.mobilneprojekat_1.database.AppDatabase
import com.example.mobilneprojekat_1.database.entities.CatDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/** Single source of truth
 *  for the whole application
 */
class CatRepository @Inject constructor(
    private val database: AppDatabase,
    private val catsApi: MyApiService,
){

//    private val catsApi : MyApiService = Client.retrofit.create(MyApiService::class.java)
    private var cats = MutableStateFlow(listOf<CatDbModel>())

    // ova funkcija poziva API
    suspend fun fetchCatBreeds(): List<CatApiModel> {
        return catsApi.getListOfBreeds()
    }

    suspend fun fetchCatDetails(catId: String): Flow<CatDbModel?> {
        return database.catDao().fetchCatById(catId)
    }

    fun setCats(catDbModels: List<CatDbModel>) {
        this.cats.value = catDbModels
    }
}