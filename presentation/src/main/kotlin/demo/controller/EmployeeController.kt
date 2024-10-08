package demo.controller

import domain.common.PageRequest
import domain.common.PageResponse
import domain.auth.entity.User
import domain.employee.dto.EmployeeInsertRequest
import domain.employee.dto.EmployeeUpdateRequest
import domain.employee.entity.Employee
import domain.employee.service.EmployeeService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import demo.common.CommonApiResponse
import domain.annotation.Authorization
import domain.annotation.LoginUser
import domain.common.validator.EmployeeUpdateValidator
import jakarta.annotation.PostConstruct
import java.time.LocalDate

@RestController
@RequestMapping("/employee")
class EmployeeController(
    private val employeeService: EmployeeService,
    private val updateValidator: EmployeeUpdateValidator
) {

    @InitBinder("employeeUpdateRequest")
    fun init(webDataBinder: WebDataBinder) {
        webDataBinder.addValidators(updateValidator)
    }

    @GetMapping("/test")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/{empNo}")
    fun getEmployee(
        @LoginUser user: User,
        @PathVariable empNo: Int
    ): CommonApiResponse<Employee> {
        val employee = employeeService.findEmployeeByEmpNo(empNo)
        return CommonApiResponse(
            success = true,
            data = employee
        )
    }

    @GetMapping("/name/{firstName}")
    fun getEmployee(
        @PathVariable firstName: String
    ): CommonApiResponse<Employee> {
        val employee = employeeService.findEmployeeByName(firstName)
        return CommonApiResponse(
            success = true,
            data = employee
        )
    }

    @GetMapping
    fun getEmployees(
        @LoginUser user: User,
        @RequestParam("startDate") startDate: LocalDate,
        @RequestParam("endDate") endDate: LocalDate,
        @RequestParam("pageNumber") pageNumber: Int = 0,
        @RequestParam("size") size: Int = 100
    ): PageResponse<Employee> {
        val pageRequest = PageRequest.of(pageNumber, size)
        return employeeService.findEmployeeByPeriod(startDate, endDate, pageRequest)
    }

    @PostMapping
    @domain.annotation.Authorization("MANAGER")
    fun insertEmployee(
        @LoginUser user: User,
        @Validated @RequestBody insertRequest: EmployeeInsertRequest
    ): CommonApiResponse<Employee> {
        return CommonApiResponse(
            success = true,
            data = employeeService.insertEmployee(insertRequest)
        )
    }

    @PutMapping("/{empNo}")
    fun updateEmployee(
        @LoginUser user: User,
        @PathVariable empNo: Int,
        @Validated @RequestBody updateRequest: EmployeeUpdateRequest
    ): CommonApiResponse<Employee> {
        val updatedEmployee = employeeService.updateEmployeeFirstName(empNo, updateRequest)
        return CommonApiResponse(
            success = true,
            data = updatedEmployee
        )
    }

    @DeleteMapping("/{empNo}")
    fun deleteEmployee(
        @LoginUser user: User,
        @PathVariable empNo: Int
    ): CommonApiResponse<Unit> = CommonApiResponse(success = employeeService.deleteEmployee(empNo))
}