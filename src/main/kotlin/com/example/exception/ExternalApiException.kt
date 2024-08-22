package com.example.exception

import com.example.common.ErrorCode

class ExternalApiException(errorCode: ErrorCode, val serviceUrl: String): BaseServiceException(errorCode) {
}