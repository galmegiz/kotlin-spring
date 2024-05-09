package com.example.common.validator

import com.example.dto.EmployeeUpdateRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.time.LocalDate

@Component
class EmployeeUpdateValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return EmployeeUpdateRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        val updateRequest = target as EmployeeUpdateRequest
        if (updateRequest.birthDate != null) {
            if(updateRequest.birthDate > LocalDate.parse("1999-01-01"))
                errors.rejectValue("birthDate", "유효하지 않은 생일")
        }

        if (updateRequest.firstName != null) {
            if(updateRequest.firstName.isBlank())
                errors.rejectValue("firstName", "이름은 공백일 수 없습니다.")
        }

        if (updateRequest.lastName != null) {
            if(updateRequest.lastName.isBlank())
                errors.rejectValue("lastName", "이름은 공백일 수 없습니다.")
        }

        if (updateRequest.hireDate != null) {
            if(updateRequest.hireDate > LocalDate.parse("2000-01-01"))
                errors.rejectValue("hireDate", "유효하지 않은 고용일")
        }
    }
}