package com.example.test_loadmore.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected var compositeDisposable = CompositeDisposable()
    val eventLoading = MutableLiveData<Boolean>()
    val eventError = MutableLiveData<String>()


    fun showLoading(value: Boolean) {
        eventLoading.value = value
    }

    fun showError(err: String) {
        eventError.value = err
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.let {
            it.clear()
        }
    }


}

