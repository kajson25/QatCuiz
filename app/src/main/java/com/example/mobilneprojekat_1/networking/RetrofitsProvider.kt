package com.example.mobilneprojekat_1.networking

import com.example.mobilneprojekat_1.networking.controller.MyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitsProvider {

    @Provides
    @Singleton
    fun provideCatsRetrofit(@Named("CatApiRetrofit") retrofit: Retrofit): MyApiService {
        return retrofit.create(MyApiService::class.java)
    }
//    @Provides
//    @Singleton
//    fun provideCatsRetrofit(@Named("CatApiRetrofit") retrofit: Retrofit): MyApiService = retrofit.create()

}