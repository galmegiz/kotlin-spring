package domain.common.exception

import domain.common.ErrorCode
import kotlin.reflect.KClass

class ResourceNotFoundException(errorCode: ErrorCode, resourceType: KClass<*>) : BaseServiceException(errorCode) {
}