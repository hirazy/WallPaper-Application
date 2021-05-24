package com.example.test_loadmore.ui.component.detail.image

import androidx.lifecycle.ViewModel
import com.example.test_loadmore.base.ui.BaseViewModel
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.local.room.RoomDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailImageViewModel @Inject constructor(): BaseViewModel() {

    lateinit var image : ArgumentDetailImage

    fun fetchData(o: ArgumentDetailImage){
        image = o
    }

    fun clickFavorite(){

    }

}