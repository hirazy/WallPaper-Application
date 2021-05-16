package com.example.test_loadmore.di

import android.content.Context
import androidx.room.Room
import com.example.test_loadmore.KEY_DATABASE
import com.example.test_loadmore.data.local.LocalData
import com.example.test_loadmore.data.local.room.RoomDB
import com.example.test_loadmore.utils.Network
import com.example.test_loadmore.utils.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLocalRepository(@ApplicationContext context: Context): LocalData {
        return LocalData(context)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(
            context, RoomDB::class.java,
            KEY_DATABASE
        ).allowMainThreadQueries().build()
    }

}