package com.example.myapplication.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.home.entity.Json4Kotlin_Base
import com.example.myapplication.home.repository.MyRepository
import javax.inject.Inject

class MyViewModel @Inject constructor(private val myRepository: MyRepository): ViewModel() {
    var newsFeed: LiveData<Json4Kotlin_Base>? = null
    fun handleButtonClick() {
        newsFeed = myRepository.fetchData()
    }
}