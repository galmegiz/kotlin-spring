package com.example.controller

import com.example.common.CommonApiResponse
import com.example.dto.UserLoginRequest
import com.example.dto.UserLoginResponse
import com.example.dto.UserSignUpResponse
import com.example.dto.UserSignupRequest
import com.example.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/user/signup")
    fun signup(request: UserSignupRequest): CommonApiResponse<UserSignUpResponse>
         = CommonApiResponse(
            success = true,
            data = userService.signupUser(request))

    @PostMapping("/user/login")
    fun userLogin(
        request: UserLoginRequest
    ): CommonApiResponse<UserLoginResponse>
        = CommonApiResponse(
            success = true,
            data = userService.loginUser(request)
        )
}