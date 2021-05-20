package com.example.test_loadmore.ui.component.main.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(): ViewModel() {

    private var listDataPrivate =  MutableLiveData<Resource<List<Category>>>()
    val listData : LiveData<Resource<List<Category>>> get() = listDataPrivate

    fun fetchData(data: List<Category>){
        listDataPrivate.value = Resource.Success(data)
    }
}