package domain.employee.entity

import domain.common.constant.Gender
import java.time.LocalDate

data class Employee(
    val empNo: Int? = null,
    val birthDate: LocalDate,
    var firstName: String,
    var lastName: String,
    val gender: Gender,
    val hireDate: LocalDate) {
}