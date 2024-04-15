package com.example.controller

import com.example.common.CommonApiResponse
import com.example.domain.Employee
import com.example.repository.EmployeesRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employee")
class EmployeeController(
    private val memoryEmployeeRepository: EmployeesRepository
) {

    @GetMapping("/")
    fun hello(): String{
        return "hello"
    }

    @GetMapping("/{empNo}")
    fun getEmployee(@PathVariable empNo: Int): CommonApiResponse<Employee> {
        val employee = memoryEmployeeRepository.getEmployee(empNo) ?: throw NoSuchElementException()
        return CommonApiResponse(
            success = true,
            data = employee
        )
    }
}