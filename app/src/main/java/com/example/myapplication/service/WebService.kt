package com.example.myapplication.service

import com.example.myapplication.entity.Json4Kotlin_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("v2/top-headlines")
    fun getData(@Query("country") country: String,
                @Query("category") category: String,
                @Query("apiKey") apiKey: String): Call<Json4Kotlin_Base>
}