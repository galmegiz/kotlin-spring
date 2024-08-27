package demo.common.validator

import demo.employee.dto.EmployeeUpdateRequest
import demo.employee.entity.MIN_BIRTH_DATE
import demo.employee.entity.MIN_HIRE_DATE
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
            if(updateRequest.birthDate!! > LocalDate.parse(MIN_BIRTH_DATE))
                errors.rejectValue("birthDate", "유효하지 않은 생일")
        }

        if (updateRequest.firstName != null) {
            if(updateRequest.firstName!!.isBlank())
                errors.rejectValue("firstName", "이름은 공백일 수 없습니다.")
        }

        if (updateRequest.lastName != null) {
            if(updateRequest.lastName!!.isBlank())
                errors.rejectValue("lastName", "이름은 공백일 수 없습니다.")
        }

        if (updateRequest.hireDate != null) {
            if(updateRequest.hireDate!! > LocalDate.parse(MIN_HIRE_DATE))
                errors.rejectValue("hireDate", "유효하지 않은 고용일")
        }
    }
}