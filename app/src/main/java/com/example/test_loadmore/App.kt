package com.example.test_loadmore

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App: Application(){

    lateinit var app: App

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        @get:Synchronized
        lateinit var instance: App
       // lateinit var room_db: RoomDB

//        fun dataBase(): RoomDB {
//
//            room_db = Room.databaseBuilder(
//                instance, RoomDB::class.java,
//                KEY_DATABASE
//            ).allowMainThreadQueries().build()
//            return room_db
//        }
    }
}