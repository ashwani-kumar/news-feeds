package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AppViewModelFactory @Inject internal constructor(val creators: Map<Class<out ViewModel>, Provider<ViewModel>>):
ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if(creator == null){
            for ((key, value) in creators){
                if(modelClass.isAssignableFrom(key)){
                    creator = value
                    break
                }
            }
        }
        requireNotNull(creator) {"Unknown model class $modelClass"}
        return try {
            creator.get() as T
        } catch (e: Exception){
            throw RuntimeException(e)
        }
    }
}