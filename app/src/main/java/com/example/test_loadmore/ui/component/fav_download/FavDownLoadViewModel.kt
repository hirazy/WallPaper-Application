package com.example.test_loadmore.ui.component.fav_download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_loadmore.App
import com.example.test_loadmore.TYPE_FAV
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentFavDownLoad
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.data.local.LocalData
import com.example.test_loadmore.data.local.room.RoomDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavDownLoadViewModel @Inject constructor(private val localData: LocalData): ViewModel() {


    private var listDataPrivate =  MutableLiveData<Resource<List<Image>>>()
    val listData : LiveData<Resource<List<Image>>> get() = listDataPrivate

    lateinit var data: ArgumentFavDownLoad

    private var db: RoomDB

    init {
        db = App.dataBase()
    }

    fun fetchData(o: ArgumentFavDownLoad){

        data = o

        if(o.type == TYPE_FAV){
            var list = db.daoImage.getAllImages()
            listDataPrivate.value = Resource.Success(list)
        }
        else{

        }
    }

    fun reLoad(){
        if(data.type == TYPE_FAV){
            var list = db.daoImage.getAllImages()
            listDataPrivate.value = Resource.Success(list)
        }
        else{

        }
    }
}