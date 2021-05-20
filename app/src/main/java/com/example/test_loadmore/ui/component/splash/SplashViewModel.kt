package com.example.test_loadmore.ui.component.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_loadmore.base.ui.BaseViewModel
import com.example.test_loadmore.data.DataRepositorySource
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentRequestNetwork
import com.example.test_loadmore.data.dto.categories.CategoryL
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.config.TopResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var dataRepositorySource: DataRepositorySource) :
    BaseViewModel() {

    private var resultRequestPrivate = MutableLiveData<Resource<ArgumentRequestNetwork>>()
    val resultRequest: LiveData<Resource<ArgumentRequestNetwork>> get() = resultRequestPrivate

    init {
        fetchData()
    }

    private fun fetchData() {
        Log.e("fetchData", "1")

        var result = ArgumentRequestNetwork(
            TopResource(0, 0, listOf()),
            PopularResource(0, 0),
            PopularResource(0, 0),
            PopularResource(0, 0),
            listOf()
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                dataRepositorySource.requestTop().collect {
                    when (it) {
                        is Resource.Success -> {
                            result.top = it.data!!
                        }
                    }
                }

                dataRepositorySource.request4D().collect {
                    when (it) {
                        is Resource.Success -> {
                            result._4d = it.data!!
                        }
                    }

                }

                dataRepositorySource.request4K().collect {
                    when (it) {
                        is Resource.Success -> {
                            result._4k = it.data!!
                        }
                    }
                }


                dataRepositorySource.requestLive().collect {
                    when (it) {
                        is Resource.Success -> {
                            result.live = it.data!!
                        }
                    }
                }

                dataRepositorySource.requestCategory().collect {
                    when (it) {
                        is Resource.Success -> {
                            result.category = it.data!!
                        }
                    }
                }

            }
            resultRequestPrivate.value = Resource.Success(result)
        }
    }

}