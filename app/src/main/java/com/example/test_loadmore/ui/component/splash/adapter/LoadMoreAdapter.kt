package com.example.test_loadmore.ui.component.splash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_loadmore.R

class LoadMoreAdapter(var mItemList: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view: View =
                LayoutInflater.from(parent.context).inflate(
                    R.layout.card_item_loadmore,
                    parent,
                    false
                )
            LoadMoreAdapter.ItemViewHolder(view)
        } else {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadMoreAdapter.LoadingViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mItemList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            populateItemRows((holder as ItemViewHolder?)!!, position)
        } else if (holder is LoadingViewHolder) {
            showLoadingView((holder as LoadingViewHolder?)!!, position)
        }

    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {
        viewHolder.progressBar.visibility =  View.VISIBLE
    }

    private class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvItem: TextView

        init {
            tvItem = itemView.findViewById(R.id.tvItem)
        }
    }

    private class LoadingViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progressBar)
        }
    }

    override fun getItemCount(): Int {
        return mItemList!!.size
    }

    private fun populateItemRows(
        viewHolder: LoadMoreAdapter.ItemViewHolder,
        position: Int
    ) {
        val item = mItemList[position]
        viewHolder.tvItem.setText(item)
    }
}