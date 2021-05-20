package com.example.test_loadmore.ui.component.main.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_loadmore.TYPE_4K
import com.example.test_loadmore.TYPE_LIVE
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.image.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveViewModel @Inject constructor(): ViewModel() {

    private var listDataPrivate = MutableLiveData<Resource<List<Image>>>()
    val listData : LiveData<Resource<List<Image>>> get() = listDataPrivate

    var start = 0
    var end = 0

    fun fetchData(data: PopularResource){

        start = data.id_start
        end = data.id_end

        var listTmpId = ArrayList<Image>()

        for (i in start - 1 until end - 1) {
            var randId = i + 1
            listTmpId.add(Image(randId, TYPE_LIVE))
        }

        listDataPrivate.value = Resource.Success(listTmpId)
    }

}