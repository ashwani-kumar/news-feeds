package com.example.myapplication.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.NewsFeedApplication
import com.example.myapplication.NewsListAdapter
import com.example.myapplication.R
import com.example.myapplication.home.entity.Articles
import com.example.myapplication.home.viewmodel.MyViewModel
import javax.inject.Inject

class NewsFeedListFragment:Fragment() {

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var vm: MyViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_news_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as NewsFeedApplication).component.inject(this)
        vm.handleButtonClick()
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        val adapter = NewsListAdapter(arrayListOf<Articles>())
        recyclerView.adapter = adapter
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(object: ItemClickSupport.OnItemClickListener{
            override fun onItemClicked(
                recyclerView: RecyclerView,
                adapterPosition: Int?,
                it: View
            ) {
                if(adapterPosition != null) {
                    val article = vm.getArticleAtPosition(adapterPosition)
                    if (article != null) {
                        NewsFeedListFragmentDirections.newsFeedToNewsDetailAction(
                            article.urlToImage?: "",
                            article.title,
                            article.description,
                            article.publishedAt)
                            .apply {
                                findNavController().navigate(this)
                        }
                    }
                }
            }

        })
        vm.newsFeed?.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter.list = it.articles as ArrayList<Articles>
                adapter.notifyDataSetChanged()
            }
        })
    }
}