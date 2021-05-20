package com.example.test_loadmore.ui.component.detail.image

import androidx.lifecycle.ViewModel
import com.example.test_loadmore.base.ui.BaseViewModel
import com.example.test_loadmore.data.local.room.RoomDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailImageViewModel @Inject constructor(var db: RoomDB): BaseViewModel() {

    init {
        fetchData()
    }

    fun fetchData(){

    }

}