package com.example.test_loadmore.data.remote

import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.categories.Category
import com.example.test_loadmore.data.dto.categories.CategoryL
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.config.TopResource
import com.example.test_loadmore.data.error.NETWORK_ERROR
import com.example.test_loadmore.data.error.NO_INTERNET_CONNECTION
import com.example.test_loadmore.data.remote.service.CategoryService
import com.example.test_loadmore.data.remote.service.PopularService
import com.example.test_loadmore.data.remote.service.TopService
import com.example.test_loadmore.data.remote.service.WallPaperService
import com.example.test_loadmore.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) :
    RemoteDataSource {

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    override suspend fun requestTop(): Resource<TopResource> {
        val topService = serviceGenerator.createService(TopService::class.java)
        return when (val response = processCall { topService.fetchData("top.json") }) {
            is TopResource -> {
                Resource.Success(response as TopResource)
            }
            else -> {
                Resource.DataError(response as Int)
            }
        }
    }

    override suspend fun request4D(): Resource<PopularResource> {
        val popularService = serviceGenerator.createService(PopularService::class.java)
        return when (val response = processCall { popularService.fetchData("4d.json") }) {
            is PopularResource -> {
                Resource.Success(response as PopularResource)
            }
            else -> {
                Resource.DataError(response as Int)
            }
        }
    }

    override suspend fun request4K(): Resource<PopularResource> {
        val popularService = serviceGenerator.createService(PopularService::class.java)
        return when (val response = processCall { popularService.fetchData("4k.json") }) {
            is PopularResource -> {
                Resource.Success(response as PopularResource)
            }
            else -> {
                Resource.DataError(response as Int)
            }
        }
    }

    override suspend fun requestLive(): Resource<PopularResource> {
        val popularService = serviceGenerator.createService(PopularService::class.java)
        return when (val response = processCall { popularService.fetchData("live.json") }) {
            is PopularResource -> {
                Resource.Success(response as PopularResource)
            }
            else -> {
                Resource.DataError(response as Int)
            }
        }
    }

    override suspend fun requestCategories(): Resource<List<Category>> {
        val categoryService = serviceGenerator.createService(CategoryService::class.java)
        return when (val response = processCall { categoryService.fetchData("category.json") }) {
            is List<*> -> {
                Resource.Success(response as List<Category>)
            }
            else -> {
                Resource.DataError(response as Int)
            }
        }
    }

    override suspend fun requestViewAll(): Resource<List<CategoryL>> {
        val categoryService = serviceGenerator.createService(CategoryService::class.java)
        return when (val response = processCall { categoryService.fetchData1("categories.json") }) {
            is List<*> -> {
                Resource.Success(response as List<CategoryL>)
            }
            else -> {
                Resource.DataError(response as Int)
            }
        }
    }
}