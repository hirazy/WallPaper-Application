package com.example.test_loadmore.base.data.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.test_loadmore.R
import javax.inject.Inject

class PrefHelperImpl @Inject constructor(var context: Application) :
    PrefHelper {
    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    private val editor = mPrefs.edit()

    override fun setData(key: String, s: String) {
        editor.putString(key, s)
        editor.commit()
    }

    override fun getData(key: String): String =
        mPrefs
            .getString(key, "").toString();


}

