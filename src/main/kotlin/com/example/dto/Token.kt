package com.example.dto

import java.util.*

data class Token(
    val granType: String,
    val accessToken: String,
    val accessTokenExpiresIn: Date,
)
