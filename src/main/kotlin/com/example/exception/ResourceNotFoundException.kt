package com.example.exception

import com.example.common.ErrorCode
import kotlin.reflect.KClass

class ResourceNotFoundException(errorCode: ErrorCode, resourceType: KClass<*>) : BaseServiceException(errorCode) {
}