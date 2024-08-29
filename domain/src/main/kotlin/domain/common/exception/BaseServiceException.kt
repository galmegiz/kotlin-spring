package domain.common.exception

import domain.common.ErrorCode

open class BaseServiceException(val errorCode: ErrorCode, cause: Throwable?) : RuntimeException(errorCode.message, cause) {
    constructor(errorCode: ErrorCode) : this(errorCode, null)
}