package com.example.test_loadmore.data

import com.example.test_loadmore.data.local.LocalData
import javax.inject.Inject

class DataRepository @Inject constructor(private val localData: LocalData): DataRepositorySource{

}