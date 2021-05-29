package com.example.test_loadmore.ui.component.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test_loadmore.R
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        
    }

    override fun onPause() {
        Log.e("NavActivity", "onPause")
        super.onPause()
    }

    override fun onDestroy() {

        super.onDestroy()
        Log.e("NavActivity", "onDestroy")
    }


}