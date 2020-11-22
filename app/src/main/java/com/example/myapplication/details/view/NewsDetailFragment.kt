package com.example.myapplication.details.view

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.myapplication.NewsFeedApplication
import com.example.myapplication.R
import com.example.myapplication.details.viewmodel.NewsDetailViewModel
import com.example.myapplication.utils.toBitmap
import kotlinx.coroutines.*
import java.net.URL
import javax.inject.Inject

class NewsDetailFragment: Fragment() {

    private val args by navArgs<NewsDetailFragmentArgs>()
    @Inject
    lateinit var vm: NewsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (requireActivity().application as NewsFeedApplication).component.inject(this)
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.textView2)

        if(!args.imageUrl.isNullOrEmpty()){
            val url = URL(args.imageUrl)
            vm.getImage(url)
            vm.image.observe(viewLifecycleOwner, Observer {
                if(it != null) {
                    imageView.setImageBitmap(it)
                }
            })
        }
        if(!args.content.isNullOrEmpty()){
            textView.text = args.content
        }
    }
}