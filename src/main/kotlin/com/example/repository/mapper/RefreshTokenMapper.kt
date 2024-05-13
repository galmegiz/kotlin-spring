package com.example.repository.mapper

import com.example.dto.RefreshToken
import com.example.repository.table.REFRESH_TOKEN_TABLE
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface RefreshTokenMapper {
    @Select("SELECT * FROM $REFRESH_TOKEN_TABLE WHERE email = #{email}")
    fun findByEmail(email: String): RefreshToken?
    @Insert("INSERT INTO $REFRESH_TOKEN_TABLE (email, token_value) VALUES (#{email}, #{tokenValue}) " +
            "ON DUPLICATE KEY UPDATE token_value = #{tokenValue}")
    fun saveRefreshToken(refreshToken: RefreshToken): Int
    @Update("UPDATE $REFRESH_TOKEN_TABLE SET token_value = #{tokenValue} WHERE email = #{email}")
    fun updateRefreshToken(refreshToken: RefreshToken): Int
    @Delete("DELETE $REFRESH_TOKEN_TABLE WHERE email = #{email}")
    fun deleteRefreshToken(email: String): Int
}