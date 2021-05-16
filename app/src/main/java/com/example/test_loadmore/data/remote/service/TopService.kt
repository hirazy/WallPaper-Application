package com.example.test_loadmore.data.remote.service

import com.example.test_loadmore.data.dto.config.TopResource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TopService {
    @GET("{path}")
    suspend fun fetchData(@Path("path") path: String): Response<TopResource>
}