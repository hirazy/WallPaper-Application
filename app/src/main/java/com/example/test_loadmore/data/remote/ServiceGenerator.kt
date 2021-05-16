package com.example.test_loadmore.data.remote

import com.example.test_loadmore.BASE_URL
import com.example.test_loadmore.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 5   //In minutes
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 5   //In minutes

@Singleton
class ServiceGenerator @Inject constructor() {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    private val retrofit: Retrofit
    private val header = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(header)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.MINUTES)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.MINUTES)
        var client = okHttpBuilder.build()
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(client).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}