package com.example.test_loadmore.service.appservice

import android.app.Service;
import android.content.Intent
import android.os.IBinder

class Service: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}