package com.example.test_loadmore.data.remote.service

import com.example.test_loadmore.data.dto.image.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {
    @GET("{path}")
    suspend fun fetchImages(@Path("path") path: String): Response<List<Image>>
}