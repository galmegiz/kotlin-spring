package com.example.exception

import com.example.common.ErrorCode

open class BaseServiceException(val errorCode: ErrorCode, cause: Throwable?) : RuntimeException(errorCode.message, cause) {
    constructor(errorCode: ErrorCode) : this(errorCode, null)
}