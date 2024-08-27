package demo.employee.service

import demo.common.ErrorCode
import demo.common.Page
import demo.common.PageRequest
import demo.common.PageResponse
import demo.common.Log
import demo.common.exception.ResourceNotFoundException
import demo.employee.dto.EmployeeInsertRequest
import demo.employee.dto.EmployeeUpdateRequest
import demo.employee.entity.Employee
import demo.employee.repository.EmployeesRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class EmployeeService(
    private val mybatisEmployeeRepository: EmployeesRepository
) : Log {

    fun insertEmployee(insertRequest: EmployeeInsertRequest): Employee {
        val newEmployee = Employee(
            birthDate = insertRequest.birthDate,
            firstName = insertRequest.firstName,
            lastName = insertRequest.lastName,
            gender = insertRequest.gender,
            hireDate = insertRequest.hireDate
        )
        mybatisEmployeeRepository.saveEmployee(newEmployee)
        log.info("{}", newEmployee)
        return newEmployee
    }

    @Transactional(readOnly = true)
    fun findEmployeeByEmpNo(empNo: Int): Employee {
        return findEmployeeOrThrow(empNo)
    }

    fun updateEmployeeFirstName(empNo: Int, updateRequest:
    EmployeeUpdateRequest
    ): Employee {
        check(mybatisEmployeeRepository.updateEmployee(empNo,
            firstName = updateRequest.firstName,
            lastName = updateRequest.lastName,
            birthDate = updateRequest.birthDate,
            gender = updateRequest.gender,
            hireDate = updateRequest.hireDate))
        return findEmployeeByEmpNo(empNo)
    }

    @Transactional(readOnly = true)
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

    @Transactional
    fun deleteEmployee(empNo: Int): Boolean{
        val employee = findEmployeeOrThrow(empNo)
        check(mybatisEmployeeRepository.deleteEmployee(empNo)){ "delete employee fail!!" }
        check(mybatisEmployeeRepository.saveDeletedEmployee(
            empNo = employee.empNo!!,
            birthDate = employee.birthDate,
            firstName = employee.firstName,
            lastName = employee.lastName,
            gender = employee.gender,
            hireDate = employee.hireDate,
            deletedDate = LocalDate.now()
        )){ "back up employee fail!!" }
        return true
    }

    @Transactional(readOnly = true)
    private fun findEmployeeOrThrow(empNo: Int): Employee = mybatisEmployeeRepository.findByEmpNo(empNo)
        ?: throw ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, Employee::class)

    @Transactional(readOnly = true)
    fun findEmployeeByName(name: String): Employee = mybatisEmployeeRepository.findByFirstName(name)
        ?: throw ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, Employee::class)
}