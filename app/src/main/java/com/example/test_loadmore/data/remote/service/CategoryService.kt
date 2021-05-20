package com.example.test_loadmore.data.remote.service

import com.example.test_loadmore.data.dto.categories.Category
import com.example.test_loadmore.data.dto.categories.CategoryL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryService {
    @GET("{path}")
    suspend fun fetchData(@Path("path") path: String): Response<List<Category>>

    @GET("{path}")
    suspend fun fetchData1(@Path("path") path: String): Response<List<CategoryL>>
}