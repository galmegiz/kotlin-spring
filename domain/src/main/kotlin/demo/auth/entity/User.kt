package demo.auth.entity

import java.time.LocalDate
import java.time.LocalDateTime

class User(
    val id: Long?,
    val email: String,
    val password: String?,
    val userName: String,
    var lastLoginAt: LocalDateTime? = null,
    val deletedAt: LocalDate?,
    var roleName: String? = "VISITOR"
) {

    constructor(id: Long?,
                email: String,
                password: String?,
                userName: String,
                lastLoginAt: LocalDateTime? = null,
                deletedAt: LocalDate?) : this(id, email, password, userName, lastLoginAt, deletedAt, "VISITOR")


    companion object{
        fun of(
            email: String,
            password: String,
            userName: String
        ): User {
            return User(
                id = null,
                email = email,
                password = password,
                userName = userName,
                lastLoginAt = null,
                deletedAt = null
            )
        }
    }

    fun isWithdrawal(): Boolean{
        return deletedAt != null
    }

    fun updateLastLoginTime() {
        this.lastLoginAt = LocalDateTime.now()
    }
}