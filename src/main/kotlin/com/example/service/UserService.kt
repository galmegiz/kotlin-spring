package com.example.service

import com.example.common.ErrorCode
import com.example.domain.User
import com.example.dto.Login
import com.example.dto.UserLoginRequest
import com.example.dto.UserLoginResponse
import com.example.dto.UserSignUpResponse
import com.example.dto.UserSignupRequest
import com.example.exception.SecurityException
import com.example.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationService: AuthenticationService,
) {

    fun signupUser(request: UserSignupRequest): UserSignUpResponse{
        val encodedPassword = passwordEncoder.encode(request.password)
        val newUser = User.of(request.email, encodedPassword, request.userName)
        userRepository.saveUser(newUser)
        requireNotNull(newUser.id)
        val token = authenticationService.generateToken(newUser)

        return UserSignUpResponse(newUser.id, request.email, request.userName,
            loginInfo = Login(token))
    }

    fun loginUser(request: UserLoginRequest): UserLoginResponse{
        val user: User = findValidUserByEmail(request.email)

        if(!passwordEncoder.matches(request.password, user.password))
            throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)

        user.updateLastLoginTime()
        requireNotNull(user.id)
        check(userRepository.updateUser(userId = user.id, lastLoginAt = user.lastLoginAt)){"update last login time fail"}

        val token = authenticationService.generateToken(user)
        return UserLoginResponse(loginInfo = Login(token))
    }

    @Transactional(readOnly = true)
    fun findValidUserByEmail(email: String): User {
        val user: User = userRepository.findByUserEmail(email) ?: throw SecurityException(ErrorCode.USER_NOT_FOUND)
        check(!user.isWithdrawal()) { "The user(${user.email}) is withdraw" }
        return user
    }
}