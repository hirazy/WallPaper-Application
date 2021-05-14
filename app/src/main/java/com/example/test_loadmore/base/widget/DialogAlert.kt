package com.example.test_loadmore.base.widget

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.test_loadmore.R

class DialogAlert(context: Context, listener: Listener) {
    interface Listener {
        fun onAlertButtonClick()
        fun onAlertCloseClick()
    }

    private val tvTitle: TextView
    private val buttonDialog: TextView
    private val imgClose: ImageView
    private val alertDialog: AlertDialog?

    init {
        val builder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_alert, null)
        builder.setView(dialogView)
        alertDialog = builder.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false)

        tvTitle = dialogView.findViewById(R.id.textTitle)
        buttonDialog = dialogView.findViewById(R.id.buttonDialog)
        imgClose = dialogView.findViewById(R.id.imgClose)

        imgClose.setOnClickListener {
            hideDialog()
            listener.onAlertCloseClick()
        }
        buttonDialog.setOnClickListener {
            hideDialog()
            listener.onAlertButtonClick()
        }
    }

    fun showDialog(title: String, button: String) {
        tvTitle.text = title
        buttonDialog.text = button
        alertDialog?.show()
    }

    fun hideDialog() {
        if (alertDialog != null && alertDialog.isShowing) {
            alertDialog.dismiss()
        }
    }
}