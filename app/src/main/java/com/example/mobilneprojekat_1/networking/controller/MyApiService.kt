package com.example.mobilneprojekat_1.networking.controller

import com.example.mobilneprojekat_1.networking.CatApiModel
import retrofit2.http.GET

interface MyApiService {

    @GET("breeds") // to znaci da je putnja /breeds
    suspend fun getListOfBreeds(): List<CatApiModel>

//    @GET("breedDetails")

}