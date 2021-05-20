package com.example.test_loadmore.ui.component.main._4k

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_loadmore.TYPE_4D
import com.example.test_loadmore.TYPE_4K
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.image.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Fragment4KViewModel @Inject constructor(): ViewModel() {

    private var listDataPrivate = MutableLiveData<Resource<List<Image>>>()
    val listData: LiveData<Resource<List<Image>>> get() = listDataPrivate

    var start = 0
    var end = 0

    init {

    }

    fun fetchData(it: PopularResource) {
        start = it.id_start
        end = it.id_end

        var listTmpId = ArrayList<Image>()

        var range = end - start + 1

        var cnt = 0

        for (i in 0 until end - 1) {
            var randId = i + 1
            listTmpId.add(Image(randId, TYPE_4K))
        }
        listDataPrivate.value = Resource.Success(listTmpId)
    }
}