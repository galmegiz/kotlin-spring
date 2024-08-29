package domain.auth.dto

import domain.common.constant.OAuthChannelType

data class UserLoginRequest(
    val email: String,
    val password: String,
    val oAuthChannelType: OAuthChannelType
)