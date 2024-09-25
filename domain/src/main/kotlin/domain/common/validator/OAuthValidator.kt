package domain.common.validator

import domain.annotation.ValidOAuth
import domain.common.DomainLog
import domain.common.constant.OAuthChannelType
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class OAuthValidator : ConstraintValidator<ValidOAuth, Enum<OAuthChannelType>>, DomainLog {
    private lateinit var annotation: ValidOAuth

    override fun initialize(constraintAnnotation: ValidOAuth) {
        this.annotation = constraintAnnotation
    }

    override fun isValid(value: Enum<OAuthChannelType>?, context: ConstraintValidatorContext?): Boolean {
        // enum을 순회하고 싶은 경우 아래 코드 사용
        // val enumValues: Array<out Enum<OAuthChannelType>> = this.annotation.enumClass.java.enumConstants

        return when (value as OAuthChannelType) {
            OAuthChannelType.KAKAO -> true
            OAuthChannelType.GOOGLE -> false
        }
    }
}