package com.example.myapplication.home.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.home.entity.Json4Kotlin_Base
import com.example.myapplication.home.service.MyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyRepository @Inject constructor(val service: MyService) {
    //private var service: MyService = MyService("http://newsapi.org/")

    fun fetchData(): LiveData<Json4Kotlin_Base> = service.getData("us", "business")

}