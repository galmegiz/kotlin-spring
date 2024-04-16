package com.example.service

import com.example.common.ErrorCode
import com.example.domain.Employee
import com.example.exception.ResourceNotFoundException
import com.example.repository.EmployeesRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val mybatisEmployeeRepository: EmployeesRepository
) {
    fun findEmployeeByEmpNo(empNo: Int): Employee {
        return mybatisEmployeeRepository.findByEmpNo(empNo)
            ?: throw ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, Employee::class)
    }
}