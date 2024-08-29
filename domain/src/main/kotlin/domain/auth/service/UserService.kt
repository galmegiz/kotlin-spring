package domain.auth.service

import domain.common.ErrorCode
import domain.auth.dto.Login
import domain.auth.dto.UserLoginRequest
import domain.auth.dto.UserLoginResponse
import domain.auth.dto.UserSignUpResponse
import domain.auth.dto.UserSignupRequest
import domain.auth.entity.User
import domain.auth.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import domain.common.exception.SecurityException

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
            loginInfo = Login(token)
        )
    }

    fun loginUser(request: UserLoginRequest): UserLoginResponse {
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
        val user: User = userRepository.findByUserEmail(email)
            ?: throw SecurityException(ErrorCode.USER_NOT_FOUND)
        check(!user.isWithdrawal()) { "The user(${user.email}) is withdraw" }
        return user
    }
}