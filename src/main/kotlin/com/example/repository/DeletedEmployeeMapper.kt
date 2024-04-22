package com.example.repository

import com.example.constant.Gender
import com.example.repository.table.DELETED_EMPLOYEE_TABLE
import com.example.repository.table.EMPLOYEE_TABLE
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.jdbc.SQL
import java.time.LocalDate

@Mapper
interface DeletedEmployeeMapper {

    @InsertProvider(type = DeletedEmployeeSqlBuilder::class, method = "buildInsertDeletedEmployee")
    fun saveDeletedEmployee(
        empNo: Int,
        birthDate: LocalDate,
        firstName: String,
        lastName: String,
        gender: Gender,
        hireDate: LocalDate,
        deletedDate: LocalDate
    ): Boolean

    class DeletedEmployeeSqlBuilder {
        fun buildInsertDeletedEmployee(
            empNo: Int,
            birthDate: LocalDate,
            firstName: String,
            lastName: String,
            gender: Gender,
            hireDate: LocalDate,
            deletedDate: LocalDate
        ): String {
            return object : SQL() {
                init {
                    INSERT_INTO(DELETED_EMPLOYEE_TABLE)
                    VALUES("emp_no", "#{empNo}")
                    VALUES("birth_date", "#{birthDate}")
                    VALUES("first_name", "#{firstName}")
                    VALUES("last_name", "#{lastName}")
                    VALUES("gender", "#{gender}")
                    VALUES("hire_date", "#{hireDate}")
                    VALUES("deleted_date", "#{deletedDate}")
                }
            }.toString()
        }
    }
}