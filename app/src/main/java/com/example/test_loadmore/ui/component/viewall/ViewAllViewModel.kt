package com.example.test_loadmore.ui.component.viewall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_loadmore.For_You
import com.example.test_loadmore.data.DataRepositorySource
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.image.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewAllViewModel @Inject constructor(var dataRepositorySource: DataRepositorySource) :
    ViewModel() {

    private var listDataPrivate = MutableLiveData<Resource<List<Image>>>()
    val listData: LiveData<Resource<List<Image>>> get() = listDataPrivate

    var setImage: MutableSet<Int> = hashSetOf()

    fun fetchData(name: String) {

        viewModelScope.launch {
            dataRepositorySource.requestViewAll().collect {

                var start = 0
                var end = 0

                var ind = 0

                when (it) {
                    is Resource.Success -> {
                        var list = it.data

                        if (name == For_You) {
                            start = list?.get(0)!!.id_start
                            end = list[list.size - 1].id_end
                        } else {

                            for(i in list!!.indices){
                                if(list[i].name == name){
                                    start = list[i].id_start
                                    end = list[i].id_end
                                    ind = i
                                    break
                                }
                            }
                        }

                        var listImage = ArrayList<Image>()

                        for(i in start until end + 1){
                            listImage.add(Image(i, ind))
                        }
                        listDataPrivate.value = Resource.Success(listImage)
                    }
                }
            }
        }
    }

    fun loadMore() {

    }
}