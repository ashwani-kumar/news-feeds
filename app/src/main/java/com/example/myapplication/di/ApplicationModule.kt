package com.example.myapplication.di

import com.example.myapplication.repository.NewsFeedRepository
import com.example.myapplication.service.NewsFeedService
import com.example.myapplication.service.WebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun provideWebService(): WebService{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideMyService(webService: WebService) = NewsFeedService(webService, "aad88a812b1b432d983b86e75524c7f6")

    @Singleton
    @Provides
    fun provideMyRepository(service: NewsFeedService) = NewsFeedRepository(service)
}