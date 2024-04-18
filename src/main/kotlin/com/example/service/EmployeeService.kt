package com.example.service

import com.example.common.ErrorCode
import com.example.common.Page
import com.example.common.PageRequest
import com.example.common.PageResponse
import com.example.domain.Employee
import com.example.exception.ResourceNotFoundException
import com.example.repository.EmployeesRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class EmployeeService(
    private val mybatisEmployeeRepository: EmployeesRepository
) {
    fun findEmployeeByEmpNo(empNo: Int): Employee {
        return mybatisEmployeeRepository.findByEmpNo(empNo)
            ?: throw ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, Employee::class)
    }

    fun updateEmployeeFirstName(empNo: Int, firstName: String): Employee {
        check(mybatisEmployeeRepository.updateEmployee(empNo, firstName = firstName))
        return findEmployeeByEmpNo(empNo)
    }

    fun findEmployeeByPeriod(startDate: LocalDate, endDate: LocalDate, pageRequest: PageRequest): PageResponse<Employee> {
        val employees: Page<Employee> = mybatisEmployeeRepository.findEmployeeBetweenStartDateAndEndDate(startDate, endDate, pageRequest)
        return PageResponse(
            data = employees.content,
            currentPage = employees.pageNumber,
            pageSize = employees.size,
            lastPage = employees.totalPageCount,
            next = !employees.isEnd
        )
    }
}