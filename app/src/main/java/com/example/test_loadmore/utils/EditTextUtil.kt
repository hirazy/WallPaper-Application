package com.example.test_loadmore.utils

import android.widget.EditText
import androidx.databinding.BindingAdapter

interface OnFocusLostListener {
    fun onFocusLost(view: EditText)
}

@BindingAdapter("app:onFocusLost")
fun EditText.onFocusLost(callback: OnFocusLostListener) {
    setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) { callback.onFocusLost(this) }
    }
}