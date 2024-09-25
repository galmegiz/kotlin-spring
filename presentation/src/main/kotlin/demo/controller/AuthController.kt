package demo.controller

import domain.common.ErrorCode
import domain.auth.dto.RefreshToken
import domain.auth.dto.Token
import domain.auth.service.AuthenticationService
import domain.common.exception.SecurityException
import domain.common.util.TokenUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import demo.common.CommonApiResponse

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
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
            throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }

        return CommonApiResponse(data = authenticationService.updateToken(tokenRefreshRequest))
    }

}