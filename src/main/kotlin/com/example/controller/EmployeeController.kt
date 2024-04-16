package com.example.controller

import com.example.common.CommonApiResponse
import com.example.common.ErrorCode
import com.example.domain.Employee
import com.example.exception.ResourceNotFoundException
import com.example.repository.EmployeesRepository
import com.example.repository.MybatisEmployeeRepository
import com.example.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping("/")
    fun hello(): String{
        return "hello"
    }

    @GetMapping("/{empNo}")
    fun getEmployee(@PathVariable empNo: Int): CommonApiResponse<Employee> {
        val employee = employeeService.findEmployeeByEmpNo(empNo)
        return CommonApiResponse(
            success = true,
            data = employee
        )
    }
}