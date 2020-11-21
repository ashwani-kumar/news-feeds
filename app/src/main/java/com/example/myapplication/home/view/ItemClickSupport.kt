package com.example.myapplication.home.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
import com.example.myapplication.R

class ItemClickSupport(private var recyclerView: RecyclerView) {
    private var onItemClickListener: OnItemClickListener? = null

    private var onClickListener = View.OnClickListener {
        if (onItemClickListener != null){
            val viewHolder = recyclerView.getChildViewHolder(it)
            onItemClickListener?.onItemClicked(recyclerView, viewHolder?.adapterPosition, it)
        }
    }

    private var onChildAttachStateChangeListener = object: OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            if (onItemClickListener != null){
                view.setOnClickListener(onClickListener)
            }
        }

        override fun onChildViewAttachedToWindow(view: View) {}
    }

    init {
        recyclerView.addOnChildAttachStateChangeListener(onChildAttachStateChangeListener)
    }

    companion object {
        fun addTo(recyclerView: RecyclerView): ItemClickSupport{
            var support = recyclerView.getTag(R.id.item_click_support) as ItemClickSupport?
            if(support == null){
                support = ItemClickSupport(recyclerView)
            }
            return support
        }

        fun removeFrom(recyclerView: RecyclerView): ItemClickSupport?{
            val support = recyclerView.getTag(R.id.item_click_support) as ItemClickSupport?
            support?.detach(recyclerView)
            return support
        }
    }

    private fun detach(recyclerView: RecyclerView) {
        recyclerView.addOnChildAttachStateChangeListener(onChildAttachStateChangeListener)
        recyclerView.setTag(R.id.item_click_support, null)
    }

    fun setOnItemClickListener(listener: OnItemClickListener): ItemClickSupport? {
        onItemClickListener = listener
        return this
    }

    interface OnItemClickListener {
        fun onItemClicked(
            recyclerView: RecyclerView,
            adapterPosition: Int?,
            it: View
        )
    }
}