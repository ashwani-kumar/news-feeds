package com.example.myapplication.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.entity.Json4Kotlin_Base
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsFeedService @Inject constructor(private val service: WebService, private val apiKey: String) {

    fun getData(country: String, category: String): LiveData<Json4Kotlin_Base> {
        val data = MutableLiveData<Json4Kotlin_Base>()
        service.getData(country, category, apiKey).enqueue(object :Callback<Json4Kotlin_Base> {
            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: Response<Json4Kotlin_Base>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
                print(t.localizedMessage)
            }
        })
        return data
    }
}