package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.entity.Json4Kotlin_Base
import com.example.myapplication.service.NewsFeedService
import javax.inject.Inject

class NewsFeedRepository @Inject constructor(val service: NewsFeedService) {

    fun fetchData(): LiveData<Json4Kotlin_Base> = service.getData("us", "business")

}