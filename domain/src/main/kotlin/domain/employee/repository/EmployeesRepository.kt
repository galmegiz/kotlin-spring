package domain.employee.repository

import domain.common.Page
import domain.common.PageRequest
import domain.common.constant.Gender
import domain.employee.entity.Employee
import java.time.LocalDate

interface EmployeesRepository {
    fun saveEmployee(
       employee: Employee
    ): Int

    fun findByEmpNo(empNo: Int): Employee?

    fun findByFirstName(firstName: String): Employee?

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