package demo.employee.entity

import demo.common.constant.Gender
import java.time.LocalDate

data class Employee(
    val empNo: Int? = null,
    val birthDate: LocalDate,
    var firstName: String,
    var lastName: String,
    val gender: Gender,
    val hireDate: LocalDate) {
}