package com.example.repository

import com.example.constant.Gender
import org.apache.ibatis.jdbc.SQL
import java.time.LocalDate

/*fun buildGetUserByName(@Param("fistName") firstName: String, @Param("lastName") lastName: String): String{
    return object : SQL() {
        init {
            SELECT("*")
            FROM("users")
            if (name != null) {
                WHERE("name like #{value} || '%'")
                WHERE()
            }
            ORDER_BY("id")
        }
    }.toString()
}*/

fun buildUpdateEmployee(
    empNo: Int,
    birthDate: LocalDate?,
    firstName: String?,
    lastName: String?,
    gender: Gender?,
    hireDate: LocalDate?
): String =
    object : SQL(){
        init{
            UPDATE("employees")
            birthDate?.let { SET("birthDate = #{birthDate}") }
            firstName?.let { SET("first_name = #{firstName}") }
            lastName?.let { SET("last_name = #{lastName}") }
            gender?.let { SET("gender = #{gender}") }
            hireDate?.let { SET("hireDate = #{hireDate}") }
            WHERE("empNo = #{empNo}")
        }
    }.toString()