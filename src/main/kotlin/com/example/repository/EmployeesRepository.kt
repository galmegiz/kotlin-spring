package com.example.repository

import com.example.common.Page
import com.example.common.PageRequest
import com.example.common.PageResponse
import com.example.constant.Gender
import com.example.domain.Employee
import java.time.LocalDate

interface EmployeesRepository {
    fun findByEmpNo(empNo: Int): Employee?
    fun updateEmployee(
        empNo: Int,
        birthDate: LocalDate? = null,
        firstName: String? = null,
        lastName: String? = null,
        gender: Gender? = null,
        hireDate: LocalDate? = null
    ): Boolean

    fun findEmployeeBetweenStartDateAndEndDate(startDate: LocalDate, endDate: LocalDate, pageRequest: PageRequest): Page<Employee>
    fun deleteEmployee(empNo: Int): Boolean
    fun saveDeletedEmployee(
        empNo: Int,
        birthDate: LocalDate,
        firstName: String,
        lastName: String,
        gender: Gender,
        hireDate: LocalDate,
        deletedDate: LocalDate
    ): Boolean
}