package com.example.test_loadmore.ui.component.main.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_loadmore.TYPE_IMAGE
import com.example.test_loadmore.data.DataRepositorySource
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.categories.top.CategoryTop
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class TopViewModel @Inject constructor(var dataRepositorySource: DataRepositorySource) :
    BaseViewModel() {

    private var listCategoryPrivate = MutableLiveData<Resource<List<CategoryTop>>>()
    val listCategoryLiveData: LiveData<Resource<List<CategoryTop>>> get() = listCategoryPrivate

    private var listForYouPrivate = MutableLiveData<Resource<List<Image>>>()
    val listForYouLiveData: LiveData<Resource<List<Image>>> get() = listForYouPrivate

    private var listTrendingPrivate = MutableLiveData<Resource<List<Image>>>()
    val listTrendingLiveData: LiveData<Resource<List<Image>>> get() = listTrendingPrivate

    var start = 0
    var end = 0

    private var setForYou: MutableSet<Int> = hashSetOf()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {

            listCategoryPrivate.value = Resource.Loading()

            listForYouPrivate.value = Resource.Loading()

            dataRepositorySource.requestTop().collect {

                Log.e("fetchData", it.toString())
                var response = it.data

                Log.e("response", response.toString())
                start = response!!.id_start
                end = response.id_end

                var listTemp = response.category

                var listCategory = ArrayList<CategoryTop>()

                for (i in listTemp.indices) {
                    var indRand: Int = Random().nextInt(4)
                    var urlThumb = ""
                    if (indRand == 0) {
                        urlThumb = listTemp[i].image1
                    } else if (indRand == 1) {
                        urlThumb = listTemp[i].image2
                    } else if (indRand == 2) {
                        urlThumb = listTemp[i].image3
                    } else {
                        urlThumb = listTemp[i].image4
                    }
                    listCategory.add(CategoryTop(listTemp[i].name, urlThumb))
                }

                listCategoryPrivate.value = Resource.Success(listCategory) // Category

                var tmpListForYou = ArrayList<Image>()
                var cnt = 0
                var indexStart = start
                var range = end - start + 1

                while (cnt < 20) {
                    var randInd = indexStart + Random().nextInt(range)

                    if (!setForYou.contains(randInd)) {
                        cnt++
                        setForYou.add(randInd)
                        tmpListForYou.add(Image(randInd, TYPE_IMAGE))
                    }
                }

                listForYouPrivate.value = Resource.Success(tmpListForYou) // For You

                var cnt1 = 0
                var tmpListTrending = ArrayList<Image>()
//
                while (cnt1 < 24) {
                    var randInd = indexStart + Random().nextInt(range)

                    if (!setForYou.contains(randInd)) {
                        cnt1++
                        setForYou.add(randInd)
                        tmpListTrending.add(Image(randInd, TYPE_IMAGE))
                    }
                }

                listTrendingPrivate.value = Resource.Success(tmpListTrending)

                Log.e("listTrendingPrivate", "Resource")
            }
        }
    }

    fun loadMore() {
        var cnt1 = 0
        var tmpListTrending = ArrayList<Image>(listTrendingPrivate.value!!.data!!)
        var range = end - start + 1

        while (cnt1 < 21) {
            var randInd = start + Random().nextInt(range)

            if (!setForYou.contains(randInd)) {
                cnt1++
                setForYou.add(randInd)
                tmpListTrending.add(Image(randInd, TYPE_IMAGE))
            }
        }
        listTrendingPrivate.value = Resource.Success(tmpListTrending)
    }

    fun reloadTrending() {
        var cnt1 = 0
        var tmpListTrending = ArrayList<Image>()

        var range = end - start + 1
//
        while (cnt1 < 10) {
            var randInd = start + Random().nextInt(range)

            if (!setForYou.contains(randInd)) {
                cnt1++
                setForYou.add(randInd)
                tmpListTrending.add(Image(randInd, TYPE_IMAGE))
            }
        }

        listTrendingPrivate.value = Resource.Success(tmpListTrending)
    }
}