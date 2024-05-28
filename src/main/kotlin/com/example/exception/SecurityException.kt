package com.example.exception

import com.example.common.ErrorCode

class SecurityException(errorCode: ErrorCode) : BaseServiceException(errorCode) {
}