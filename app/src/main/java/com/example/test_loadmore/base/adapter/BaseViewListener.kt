package com.example.test_loadmore.base.adapter

import com.example.test_loadmore.base.OBase


interface BaseViewListener {
    fun onItemClicked(index: Int, data: OBase)
    fun onItemLongClicked(index: Int, data: OBase)
    fun OnItemLiked(index: Int)
}