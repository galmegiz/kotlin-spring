package domain.common.validator

import domain.common.DomainLog
import domain.annotation.ValidDate
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDate

class LocalDateValidator : ConstraintValidator<ValidDate, LocalDate>, DomainLog {
    private lateinit var annotation: ValidDate
    override fun initialize(constraintAnnotation: ValidDate) {
        this.annotation = constraintAnnotation
    }

    override fun isValid(value: LocalDate, context: ConstraintValidatorContext?): Boolean {
        val fromDate = LocalDate.parse(annotation.fromLocalDate)
        return value >= fromDate
    }
}