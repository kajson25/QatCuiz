package com.example.mobilneprojekat_1.networking.controller

import com.example.mobilneprojekat_1.image.image_view.ImageApiModel
import com.example.mobilneprojekat_1.cat.CatApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiService {

    @GET("breeds") // to znaci da je putnja /breeds
    suspend fun getListOfBreeds(): List<CatApiModel>

    @GET("images/search")
    suspend fun getImagesForBreed(
        @Query("cat_ids") catId: String,
        @Query("limit") limit: Int = 20
    ): List<ImageApiModel>
}