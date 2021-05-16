package com.example.test_loadmore.error

import com.example.test_loadmore.data.error.Error


interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
