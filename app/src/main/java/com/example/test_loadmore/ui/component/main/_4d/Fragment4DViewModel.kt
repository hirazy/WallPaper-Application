package com.example.test_loadmore.ui.component.main._4d

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_loadmore.TYPE_4D
import com.example.test_loadmore.data.DataRepositorySource
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.image.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Fragment4DViewModel @Inject constructor(var dataRepositorySource: DataRepositorySource) :
    ViewModel() {

    private var list4DPrivate = MutableLiveData<Resource<List<Image>>>()
    val list4D: LiveData<Resource<List<Image>>> get() = list4DPrivate

    var setId: MutableSet<Int> = hashSetOf()

    var start = 0
    var end = 0

    init {
        // fetchData()
    }

    fun fetchData(data: PopularResource) {
        viewModelScope.launch {
            //dataRepositorySource.request4D().collect {

            start = data!!.id_start
            end = data!!.id_end

            var listTmpId = ArrayList<Image>()

            var range = end - start + 1

            var cnt = 0

            for (i in 0 until end - 1) {
                var randId = i + 1
                listTmpId.add(Image(randId, TYPE_4D))
            }

//                while (cnt < 2) {
//                    var randId = Random.nextInt(range) + start
//
//                    if (setId.contains(randId)) {
//                        listTmpId.add(Image(randId, TYPE_4D))
//                        setId.add(randId)
//                        cnt++
//                    }
//                }
            list4DPrivate.value = Resource.Success(listTmpId)
        }
        //}
    }

    fun loadMore() {

    }

}