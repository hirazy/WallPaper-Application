package com.example.test_loadmore.data

import com.example.test_loadmore.data.dto.categories.Category
import com.example.test_loadmore.data.dto.categories.CategoryL
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.config.TopResource
import com.example.test_loadmore.data.local.LocalData
import com.example.test_loadmore.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    var remoteDataRepository: RemoteData,
    private val localData: LocalData,
    var context: CoroutineContext
) : DataRepositorySource {
    override suspend fun requestTop(): Flow<Resource<TopResource>> {
        return flow {
            emit(remoteDataRepository.requestTop())
        }.flowOn(context)
    }

    override suspend fun request4D(): Flow<Resource<PopularResource>> {
        return flow {
            emit(remoteDataRepository.request4D())
        }.flowOn(context)
    }

    override suspend fun request4K(): Flow<Resource<PopularResource>> {
        return flow {
            emit(remoteDataRepository.request4K())
        }.flowOn(context)
    }

    override suspend fun requestLive(): Flow<Resource<PopularResource>> {
        return flow {
            emit(remoteDataRepository.requestLive())
        }.flowOn(context)
    }

    override suspend fun requestCategory(): Flow<Resource<List<Category>>> {
        return flow {
            emit(remoteDataRepository.requestCategories())
        }.flowOn(context)
    }

    override suspend fun requestViewAll(): Flow<Resource<List<CategoryL>>> {
        return flow{
            emit(remoteDataRepository.requestViewAll())
        }.flowOn(context)
    }

    override suspend fun addFavorite(id: String): Flow<Resource<Boolean>> {
        return flow {
//            localData.getCachedFavourites().let {
//                it.data?.toMutableSet()?.let { set ->
//                    val isAdded = set.add(id)
//                    if (isAdded) {
//                        emit(localData.cacheFavorites(set))
//                    } else {
//                        emit(Resource.Success(false))
//                    }
//                }
//                it.errorCode?.let { errorCode ->
//                    emit(Resource.DataError<Boolean>(errorCode))
//                }
//            }
        }//.flowOn(context)
    }

}