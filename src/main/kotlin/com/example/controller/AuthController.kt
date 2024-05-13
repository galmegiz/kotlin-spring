package com.example.controller

import com.example.common.CommonApiResponse
import com.example.common.ErrorCode
import com.example.common.util.TokenUtil
import com.example.dto.RefreshToken
import com.example.dto.Token
import com.example.exception.AuthenticationException
import com.example.service.AuthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val tokenUtil: TokenUtil
) {
    @GetMapping("/refreshToken")
    fun getRefreshToken(
        @RequestBody tokenRefreshRequest: RefreshToken
    ): CommonApiResponse<Token>{
        // TODO argumentResolver 또는 interceptor로 처리?
        try {
            tokenUtil.verifyToken(tokenRefreshRequest.tokenValue)
        } catch (e: Exception) {
            throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }

        return CommonApiResponse(data = authService.updateToken(tokenRefreshRequest))
    }

}