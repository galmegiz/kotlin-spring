package domain.annotation

import domain.common.validator.LocalDateValidator
import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [LocalDateValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidDate(
    val message: String = "Invalid Date",
    val fromLocalDate : String,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)
