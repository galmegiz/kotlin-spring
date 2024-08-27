package demo.common.exception

import demo.common.ErrorCode

open class BaseServiceException(val errorCode: ErrorCode, cause: Throwable?) : RuntimeException(errorCode.message, cause) {
    constructor(errorCode: ErrorCode) : this(errorCode, null)
}