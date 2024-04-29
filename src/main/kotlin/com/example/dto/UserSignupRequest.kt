package com.example.dto

import com.example.constant.OAuthChannelType

data class UserSignupRequest(
    val userName: String,
    val oAuthChannelType: OAuthChannelType,
    val password: String,
    val email: String
)
