package com.example.mobilneprojekat_1.cats.api

import com.example.mobilneprojekat_1.cats.api.model.CatApiModel
import retrofit2.http.GET

interface MyApiService {

    @GET("breeds") // to znaci da je putnja /breeds
    suspend fun getListOfBreeds(): List<CatApiModel>

//    @GET("breedDetails")

}