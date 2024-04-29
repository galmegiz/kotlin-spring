package com.example.dto

import java.util.*

data class Token(
    val grantType: String,
    val accessToken: String,
    val accessTokenExpiresIn: Date,
)
