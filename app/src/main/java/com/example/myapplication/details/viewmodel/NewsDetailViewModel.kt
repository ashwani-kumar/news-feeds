package com.example.myapplication.details.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.NewsFeedRepository
import com.example.myapplication.utils.toBitmap
import kotlinx.coroutines.*
import java.net.URL
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(private val repository: NewsFeedRepository): ViewModel() {

    private var _image: MutableLiveData<Bitmap> = MutableLiveData<Bitmap>().apply {
        value = null
    }
    var image: LiveData<Bitmap> = _image

    fun getImage(url: URL) {
        val result: Deferred<Bitmap?> = viewModelScope.async(Dispatchers.IO) {
            url.toBitmap()
        }
        viewModelScope.launch(Dispatchers.Main) {
            val bitmap : Bitmap? = result.await()
            bitmap?.apply {
                _image.value = bitmap
                image = _image
            }

        }
    }
}