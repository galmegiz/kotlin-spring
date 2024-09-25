package demo.controller

import domain.auth.dto.UserLoginRequest
import domain.auth.dto.UserLoginResponse
import domain.auth.dto.UserSignUpResponse
import domain.auth.dto.UserSignupRequest
import domain.auth.service.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import demo.common.CommonApiResponse

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody @Validated request: UserSignupRequest): CommonApiResponse<UserSignUpResponse>
         = CommonApiResponse(
            success = true,
            data = userService.signupUser(request))

    @PostMapping("/login")
    fun userLogin(
        @RequestBody request: UserLoginRequest
    ): CommonApiResponse<UserLoginResponse>
        = CommonApiResponse(
            success = true,
            data = userService.loginUser(request)
        )
}
