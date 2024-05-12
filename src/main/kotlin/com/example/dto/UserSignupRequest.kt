package com.example.dto

import com.example.common.annotation.ValidOAuth
import com.example.constant.OAuthChannelType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
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
