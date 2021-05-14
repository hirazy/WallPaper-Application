package com.example.test_loadmore.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.test_loadmore.KEY_DATABASE
import com.example.test_loadmore.data.dto.image.Image

@Database(entities = [Image::class], version = 1)
abstract class RoomDB: RoomDatabase() {

    private var instance: RoomDB? = null

    abstract val daoImage: DaoImage

    @Synchronized
    fun getInstance(ctx: Context): RoomDB {
        if (instance == null)
            instance = Room.databaseBuilder(
                ctx.applicationContext, RoomDB::class.java,
                KEY_DATABASE
            )
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build()

        return instance!!
    }

    private val roomCallback = object : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}