package domain.common.exception

import domain.common.ErrorCode

class SecurityException(errorCode: ErrorCode) : BaseServiceException(errorCode) {
}