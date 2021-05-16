package com.example.test_loadmore.data.remote.service

import com.example.test_loadmore.data.dto.config.PopularResource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PopularService{
    @GET("{path}")
    suspend fun fetchData(@Path("path") path: String): Response<PopularResource>
}