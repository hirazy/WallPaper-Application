package com.example.test_loadmore.di

import com.example.mod_gun.data.error.mapper.ErrorMapperSource
import com.example.test_loadmore.data.error.mapper.ErrorMapper
import com.example.test_loadmore.error.ErrorUseCase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// with @Module we Telling Dagger that, this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: com.example.test_loadmore.error.ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
