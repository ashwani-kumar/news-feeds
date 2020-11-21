package com.example.myapplication.di

import com.example.myapplication.home.view.MainActivity
import com.example.myapplication.home.view.NewsFeedListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    //fun inject(mainActivity: MainActivity)
    fun inject(newsFeedListFragment: NewsFeedListFragment)
}