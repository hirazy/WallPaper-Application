package com.example.test_loadmore.base.util

import androidx.lifecycle.MutableLiveData
import com.example.test_loadmore.base.OBase

inline fun <reified T : OBase> MutableLiveData<MutableList<T>>.addNewItem(
    item: T,
) {
    val oldValue = this.value ?: mutableListOf()
    oldValue.add(item)
    this.value = oldValue

}


inline fun <reified T : OBase> MutableLiveData<MutableList<T>>.addNewItemAt(
    index: Int,
    item: T,
    key: String
) {
    val oldValue = this.value ?: mutableListOf()
    oldValue.add(index, item)
    this.value = oldValue
}

inline fun <reified T : OBase> MutableLiveData<MutableList<T>>.updateItemAt(
    index: Int,
    item: T
) {
    val oldValue = this.value ?: mutableListOf()
    oldValue.set(index, item)
    this.value = oldValue
}

inline fun <reified T : OBase> MutableLiveData<MutableList<T>>.removeItemAt(
    index: Int
) {
    if (!this.value.isNullOrEmpty()) {
        val oldValue = this.value
        oldValue?.removeAt(index)
        this.value = oldValue
    } else {
        this.value = mutableListOf()
    }
}
