package com.example.test_loadmore.ui.component.nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_loadmore.R
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        MobileAds.initialize(this) {}
    }
}