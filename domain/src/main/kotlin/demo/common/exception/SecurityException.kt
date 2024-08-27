package demo.common.exception

import demo.common.ErrorCode

class SecurityException(errorCode: ErrorCode) : BaseServiceException(errorCode) {
}