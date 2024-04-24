package com.example.exception

import com.example.common.ErrorCode
import kotlin.reflect.KClass

class AuthenticationException(errorCode: ErrorCode) : BaseServiceException(errorCode) {
}