package com.example.myapplication

import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.home.entity.Articles
import java.util.zip.Inflater

class NewsListAdapter(var list: ArrayList<Articles>): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var textView: TextView = itemView.findViewById(R.id.textView)
    fun bind(str: Articles){
        textView.text = str.title
    }
}