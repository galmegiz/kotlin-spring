package com.example.domain

import com.example.constant.Gender
import java.time.LocalDate

data class Employee(
    var empNo: Int,
    val birthDate: LocalDate,
    var firstName: String,
    var lastname: String,
    val gender: Gender,
    val hireDate: LocalDate) {
}