package com.example.test_loadmore.ui.component.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_loadmore.base.ui.BaseViewModel
import com.example.test_loadmore.data.DataRepositorySource
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.categories.CategoryL
import com.example.test_loadmore.data.dto.image.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(var dataRepositorySource: DataRepositorySource): BaseViewModel() {

    private var listDataPrivate = MutableLiveData<Resource<List<Image>>>()
    val listData : LiveData<Resource<List<Image>>> get() = listDataPrivate

    var listAll = ArrayList<CategoryL>()

    var listAllImg = ArrayList<Image>()

    init {
        viewModelScope.launch {
            dataRepositorySource.requestViewAll().collect {
                when(it){
                    is Resource.Success ->{
                        listAll = ArrayList(it.data)
                    }
                }
            }
        }
    }

    fun searchData(dataSearch: String){
        var listTmp = ArrayList<Image>()
        for(i in 0 until listAll.size){
            if(listAll[i].name.contains(dataSearch)){
                var start = listAll[i].id_start
                var end = listAll[i].id_end
                for(j in start until end + 1){
                    listTmp.add(Image(j, i))
                }
            }
        }
        if(listTmp.size == 0){
            listDataPrivate.value = Resource.DataError(1)
        }
        else{
            listDataPrivate.value = Resource.Success(listTmp)
        }
    }
}