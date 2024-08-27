package demo.common.exception

import demo.common.ErrorCode
import kotlin.reflect.KClass

class ResourceNotFoundException(errorCode: ErrorCode, resourceType: KClass<*>) : BaseServiceException(errorCode) {
}