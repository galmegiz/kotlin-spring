package domain.common.exception

import domain.common.ErrorCode

class ExternalApiException(errorCode: ErrorCode, val serviceUrl: String): BaseServiceException(errorCode) {
}