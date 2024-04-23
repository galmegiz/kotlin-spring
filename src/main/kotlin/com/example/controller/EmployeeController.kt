package com.example.controller

import com.example.common.CommonApiResponse
import com.example.common.ErrorCode
import com.example.common.PageRequest
import com.example.common.PageResponse
import com.example.domain.Employee
import com.example.dto.EmployeeInsertRequest
import com.example.dto.EmployeeUpdateRequest
import com.example.exception.ResourceNotFoundException
import com.example.repository.EmployeesRepository
import com.example.repository.MybatisEmployeeRepository
import com.example.service.EmployeeService
import org.apache.ibatis.annotations.Delete
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping("/test")
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

    @GetMapping
    fun getEmployees(@RequestParam("startDate") startDate: LocalDate,
                     @RequestParam("endDate") endDate: LocalDate,
                     @RequestParam("pageNumber") pageNumber: Int = 0,
                     @RequestParam("size") size: Int = 100): PageResponse<Employee> {
        val pageRequest = PageRequest.of(pageNumber, size)
        return employeeService.findEmployeeByPeriod(startDate, endDate, pageRequest)
    }

    @GetMapping("/insert")
    fun insertEmployee(@ModelAttribute insertRequest: EmployeeInsertRequest): CommonApiResponse<Employee> {
        return CommonApiResponse(
            success = true,
            data = employeeService.insertEmployee(insertRequest)
        )
    }

    @GetMapping("/{empNo}/update")
    fun updateEmployee(@PathVariable empNo: Int,
                       @ModelAttribute updateRequest: EmployeeUpdateRequest): CommonApiResponse<Employee> {
        val updatedEmployee = employeeService.updateEmployeeFirstName(empNo, updateRequest)
        return CommonApiResponse(
            success = true,
            data = updatedEmployee
        )
    }

    @GetMapping("/{empNo}/delete")
    fun deleteEmployee(@PathVariable empNo: Int): CommonApiResponse<Unit>
        = CommonApiResponse(success = employeeService.deleteEmployee(empNo))
}