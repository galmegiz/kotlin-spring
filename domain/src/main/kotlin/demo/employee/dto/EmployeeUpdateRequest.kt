package demo.employee.dto

import demo.common.constant.Gender
import java.time.LocalDate

data class EmployeeUpdateRequest(
    val birthDate: LocalDate?,
    val firstName: String?,
    val lastName: String?,
    val gender: Gender?,
    val hireDate: LocalDate?
)