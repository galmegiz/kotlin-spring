package com.example.dto

import com.example.common.annotation.ValidDate
import com.example.constant.Gender
import jakarta.validation.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class EmployeeUpdateRequest(
    val birthDate: LocalDate?,
    val firstName: String?,
    val lastName: String?,
    val gender: Gender?,
    val hireDate: LocalDate?
)