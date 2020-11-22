package com.example.myapplication.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.entity.Articles
import com.example.myapplication.entity.Json4Kotlin_Base
import com.example.myapplication.repository.NewsFeedRepository
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(private val newsFeedRepository: NewsFeedRepository): ViewModel() {
    var newsFeed: LiveData<Json4Kotlin_Base>? = null
    fun handleButtonClick() {
        newsFeed = newsFeedRepository.fetchData()
    }

    fun getArticleAtPosition(position: Int): Articles? {
        val articles = newsFeed?.value
        return articles?.articles?.get(position)
    }
}