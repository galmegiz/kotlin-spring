package com.example.dto

import com.example.common.annotation.ValidDate
import com.example.constant.Gender
import com.example.domain.MIN_BIRTH_DATE
import com.example.domain.MIN_HIRE_DATE
import jakarta.validation.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class EmployeeInsertRequest(
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @field:ValidDate(fromLocalDate = MIN_BIRTH_DATE)
    val birthDate: LocalDate,
    @field:NotEmpty
    val firstName: String,
    @field:NotEmpty
    val lastName: String,
    val gender: Gender,
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @field:ValidDate(fromLocalDate = MIN_HIRE_DATE)
    val hireDate: LocalDate
) {

}
