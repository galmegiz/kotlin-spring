package com.example.dto

import com.example.common.annotation.ValidDate
import com.example.constant.Gender
import jakarta.validation.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class EmployeeInsertRequest(
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @field:ValidDate(fromLocalDate = "1900-01-01")
    val birthDate: LocalDate,
    @field:NotEmpty
    val firstName: String,
    @field:NotEmpty
    val lastName: String,
    val gender: Gender,
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @field:ValidDate(fromLocalDate = "2000-01-01")
    val hireDate: LocalDate
) {

}
