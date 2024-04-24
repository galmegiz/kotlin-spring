package com.example.dto

import com.example.constant.OAuthChannelType

data class UserLoginRequest(
    val email: String,
    val password: String,
    val oAuthChannelType: OAuthChannelType
)