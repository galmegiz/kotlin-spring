package com.example.repository.mapper

import com.example.domain.User
import com.example.repository.table.USER_TABLE
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.jdbc.SQL
import java.time.LocalDateTime

@Mapper
interface UserMapper {

    @Insert("INSERT INTO $USER_TABLE (user_name, password, email) values(#{userName}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "id")
    fun saveUser(
       user: User
    ): Long

    @Select("SELECT * FROM $USER_TABLE WHERE user_id = #{userId}")
    @Results(
        id = "userMap",
        value = [Result(property = "id", column = "user_id")]
    )
    fun findByUserId(userId: Long): User?

    @Select("SELECT * FROM $USER_TABLE WHERE email = #{email}")
    @ResultMap("userMap")
    fun findByUserEmail(email: String): User?

    @UpdateProvider(type = UserSqlBuilder::class, method = "buildUpdateUser")
    fun updateUser(
        id: Long,
        email: String?,
        password: String?,
        userName: String?,
        lastLoginAt: LocalDateTime?
    ): Boolean

    class UserSqlBuilder {
        fun buildUpdateUser(
            id: Long,
            email: String?,
            password: String?,
            userName: String?,
            lastLoginAt: LocalDateTime?,
        ): String {
            return object : SQL() {
                init {
                    UPDATE(USER_TABLE)
                    if (email != null) SET("email = #{email}")
                    if (password != null) SET("password = #{password}")
                    if (userName != null) SET("user_name = #{userName}")
                    if (lastLoginAt != null) SET("last_login_at = #{lastLoginAt}")
                    WHERE("user_id = #{id}")
                }
            }.toString()
        }
    }
}
