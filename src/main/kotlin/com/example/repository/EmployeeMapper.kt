package com.example.repository

import com.example.constant.Gender
import com.example.domain.Employee
import com.example.repository.table.EMPLOYEE_TABLE
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.jdbc.SQL
import java.time.LocalDate

@Mapper
interface EmployeeMapper {
    @Select("SELECT * FROM $EMPLOYEE_TABLE WHERE emp_no = #{empNo}")
    fun findByEmpNo(empNo: Int): Employee?
    @UpdateProvider(type = EmployeeSqlBuilder::class, method = "buildUpdateEmployee")
    fun updateEmployee(
        empNo: Int,
        birthDate: LocalDate?,
        firstName: String?,
        lastName: String?,
        gender: Gender?,
        hireDate: LocalDate?
    ): Boolean

    class EmployeeSqlBuilder {
        fun buildUpdateEmployee(
            empNo: Int,
            birthDate: LocalDate?,
            firstName: String?,
            lastName: String?,
            gender: Gender?,
            hireDate: LocalDate?
        ): String {
            return object : SQL() {
                init {
                    UPDATE(EMPLOYEE_TABLE)
                    if (birthDate != null) SET("birth_date = #{birthDate}")
                    if (firstName != null) SET("first_name = #{firstName}")
                    if (lastName != null) SET("last_name = #{lastName}")
                    if (gender != null) SET("gender = #{gender}")
                    if (hireDate != null) SET("hire_date = #{hireDate}")
                    WHERE("emp_no = #{empNo}")
                }
            }.toString()
        }
    }

}