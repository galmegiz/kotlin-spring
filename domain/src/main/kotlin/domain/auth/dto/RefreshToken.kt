package domain.auth.dto

class RefreshToken(
    val email: String,
    var tokenValue: String
) {
    fun updateToken(token: String): RefreshToken {
        this.tokenValue = token
        return this
    }
}