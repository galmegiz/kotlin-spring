package demo.common.exception

import demo.common.ErrorCode

class ExternalApiException(errorCode: ErrorCode, val serviceUrl: String): BaseServiceException(errorCode) {
}