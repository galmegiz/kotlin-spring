package domain.auth.dto

import domain.annotation.ValidOAuth
import domain.common.constant.OAuthChannelType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

data class UserSignupRequest(
    @field:NotEmpty
    val userName: String,
    @field:ValidOAuth(enumClass = OAuthChannelType::class)
    val oAuthChannelType: OAuthChannelType,
    @field:NotEmpty
    @field:Length(min = 6)
    val password: String,
    @field:Email
    val email: String
)
