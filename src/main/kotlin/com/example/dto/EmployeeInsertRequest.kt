package com.example.dto

import com.example.constant.Gender
import java.time.LocalDate

data class EmployeeInsertRequest(
    val birthDate: LocalDate,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val hireDate: LocalDate
) {

}
