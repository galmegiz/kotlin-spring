package demo.auth.dto

import demo.common.constant.OAuthChannelType

data class UserLoginRequest(
    val email: String,
    val password: String,
    val oAuthChannelType: OAuthChannelType
)