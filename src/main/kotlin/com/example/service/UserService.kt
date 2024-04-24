package com.example.service

import com.example.dto.UserLoginRequest
import com.example.dto.UserLoginResponse
import com.example.dto.UserSignUpResponse
import com.example.dto.UserSignupRequest

class UserService {

    fun signupUser(request: UserSignupRequest): UserSignUpResponse{
        return UserSignUpResponse()
    }

    fun loginUser(request: UserLoginRequest): UserLoginResponse{
        return UserLoginResponse()
    }
}