package com.example.mobilneprojekat_1.networking

import android.util.Log
import com.example.mobilneprojekat_1.networking.serialization.AppJson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object Client {
    private const val ROOT_URL = "https://api.thecatapi.com/v1/"

//    val instance: MyApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(ROOT_URL)
//            .client(okHttpClient)
//            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//            .build()
//            .create(MyApiService::class.java)
//    }


    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("x-api-key", "live_YLTp6qG20RACmIZh2rE9DogVFpcw1jEqnpccxSydK9DXGBUs6u1tZH2OWlWqdd5W")
                .build()
            Log.d("Mytag", newRequest.body.toString())
            chain.proceed(newRequest)
        })
        .addInterceptor(logging)
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(AppJson.asConverterFactory("application/json".toMediaType()))
        .build()
//
//    val catApiService = retrofit.create(MyApiService::class.java)

}

//GlobalScope.launch(Dispatchers.IO) {
//    val response = apiService.fetchText()
//    if (response.isSuccessful && response.body() != null) {
//        val data = response.body()
//        // Use the data as needed
//    } else {
//        // Handle request error
//    }
//}

// GlobalScope.launch(Dispatchers.IO) {
//    val response = apiService.fetchText()
//    withContext(Dispatchers.Main) {
//        if (response.isSuccessful && response.body() != null) {
//            val data = response.body()
//            // Update the UI
//        }
//    }
//}