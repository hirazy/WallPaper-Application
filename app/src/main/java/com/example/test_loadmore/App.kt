package com.example.test_loadmore

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.room.Room
import com.example.test_loadmore.data.local.room.RoomDB
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
open class App : Application() {

    lateinit var app: App

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        @get:Synchronized
        lateinit var instance: App
        lateinit var room_db: RoomDB

        fun dataBase(): RoomDB {

            room_db = Room.databaseBuilder(
                instance, RoomDB::class.java,
                KEY_DATABASE
            ).allowMainThreadQueries().build()
            return room_db
        }
    }

//    open fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val serviceChannel = NotificationChannel(
//                CHANNEL_ID,
//                "Example Service Channel",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            val manager = getSystemService(
//                NotificationManager::class.java
//            )
//            manager.createNotificationChannel(serviceChannel)
//        }
//    }

    override fun onTerminate() {
        super.onTerminate()
    }
}