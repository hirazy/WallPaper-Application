package com.example.test_loadmore.ui.component.detail.image

import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_loadmore.App
import com.example.test_loadmore.base.ui.BaseViewModel
import com.example.test_loadmore.data.DataRepository
import com.example.test_loadmore.data.DataRepositorySource
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.data.local.room.DaoImage
import com.example.test_loadmore.data.local.room.RoomDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureNanoTime

@HiltViewModel
class DetailImageViewModel @Inject constructor(var dataRepository: DataRepositorySource) :
    BaseViewModel() {

    init {

    }

    lateinit var image: Image

    private lateinit var db: RoomDB

    private var isFavouritePrivate = MutableLiveData<Resource<Boolean>>()
    val isFavourite: LiveData<Resource<Boolean>> get() = isFavouritePrivate

    fun fetchData(o: ArgumentDetailImage) {
        image = Image(o.id, o.type)

        db = App.dataBase()

        isFavourites()
    }

    private fun isFavourites() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            image.id.let {
                dataRepository.isFavourite(it.toString()).collect {
                    isFavouritePrivate.value = it
                }
            }
        }
    }

    fun addFavorite() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            image.id.let{
                dataRepository.addToFavorite(it.toString()).collect { isAdded ->
                    isFavouritePrivate.value = isAdded
                    db.daoImage.insert(image)
                }
            }

        }
    }

    fun removeFromFavorite() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            image.id.let {
                dataRepository.removeFromFavourite(it.toString()).collect { isRemoved ->
                    when (isRemoved) {
                        is Resource.Success -> {
                            isRemoved.data?.let {
                                isFavouritePrivate.value = Resource.Success(!isRemoved.data)
                                db.daoImage.deleteImageById(image.id)
                            }
                        }
                        is Resource.DataError -> {
                            isFavouritePrivate.value = isRemoved
                        }
                        is Resource.Loading -> {
                            isFavouritePrivate.value = isRemoved
                        }
                    }
                }
            }
        }

    }

}