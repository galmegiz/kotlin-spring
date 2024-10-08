package domain.annotation

import domain.common.constant.OAuthChannelType
import domain.common.validator.OAuthValidator
import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [OAuthValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidOAuth(
    val message: String = "Not Supported Channel",
    val enumClass: KClass<out Enum<OAuthChannelType>>,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)
