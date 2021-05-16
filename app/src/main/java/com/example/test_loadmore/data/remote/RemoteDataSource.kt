package com.example.test_loadmore.data.remote

import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.categories.CategoryL
import com.example.test_loadmore.data.dto.categories.CategoryList
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.config.TopResource
import com.example.test_loadmore.data.dto.image.ImageList

interface RemoteDataSource {
    suspend fun requestTop(): Resource<TopResource>

    suspend fun request4D(): Resource<PopularResource>

    suspend fun request4K(): Resource<PopularResource>

    suspend fun requestLive(): Resource<PopularResource>

    suspend fun requestCategories(): Resource<List<CategoryL>>

}