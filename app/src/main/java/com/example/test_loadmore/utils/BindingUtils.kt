package com.example.test_loadmore.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.test_loadmore.R


@BindingAdapter("image")
fun loadImage(image: ImageView, url: String) {
    Glide.with(image)
        .load(url).placeholder(R.mipmap.ic_loadding)
        .fitCenter()
        .into(image)

}