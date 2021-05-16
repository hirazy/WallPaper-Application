package com.example.test_loadmore.error

import com.example.test_loadmore.data.error.Error
import com.example.test_loadmore.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): com.example.test_loadmore.data.error.Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
