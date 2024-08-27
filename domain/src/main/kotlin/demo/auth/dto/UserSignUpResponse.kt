package demo.auth.dto

data class UserSignUpResponse(
    val id: Long,
    val email: String,
    val userName: String,
    val loginInfo: Login
)
