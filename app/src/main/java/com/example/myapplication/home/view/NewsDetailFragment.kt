package com.example.myapplication.home.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import kotlinx.coroutines.*
import org.w3c.dom.Text
import java.io.IOException
import java.net.URL

class NewsDetailFragment: Fragment() {

    private val args by navArgs<NewsDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.textView2)

        if(!args.imageUrl.isNullOrEmpty()){
            val url = URL(args.imageUrl)
            val result: Deferred<Bitmap?> = GlobalScope.async {
                url.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                // get the downloaded bitmap
                val bitmap : Bitmap? = result.await()

                // if downloaded then saved it to internal storage
                bitmap?.apply {
                    imageView.setImageBitmap(bitmap)
                }
            }
            imageView.setImageURI(Uri.parse(args.imageUrl))
        }
        if(!args.content.isNullOrEmpty()){
            textView.text = args.content
        }
    }
}

fun URL.toBitmap(): Bitmap?{
    return try {
        BitmapFactory.decodeStream(openStream())
    }catch (e:IOException){
        null
    }
}