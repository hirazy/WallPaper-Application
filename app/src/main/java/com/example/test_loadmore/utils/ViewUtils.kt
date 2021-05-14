package com.example.test_loadmore.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(isEnabled: Boolean) {
    setEnabled(isEnabled)
    alpha = if (isEnabled) 1f else 0.5f
}

fun Context.toast(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

@BindingAdapter("app:font")
fun setFont(textView: TextView, fontName: String) {
    val typeface =
        Typeface.createFromAsset(textView.context.assets, "fonts/$fontName")
    textView.typeface = typeface
}

fun Context.showPermissionRequestDialog(
    title: String,
    body: String,
    callback: () -> Unit
) {
    AlertDialog.Builder(this).also {
        it.setTitle(title)
        it.setMessage(body)
        it.setPositiveButton("Ok") { _, _ ->
            callback()
        }

    }.create().show()
}
