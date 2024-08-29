package domain.employee.service

import domain.common.ErrorCode
import domain.common.Page
import domain.common.PageRequest
import domain.common.PageResponse
import domain.common.Log
import domain.common.exception.ResourceNotFoundException
import domain.employee.dto.EmployeeInsertRequest
import domain.employee.dto.EmployeeUpdateRequest
import domain.employee.entity.Employee
import domain.employee.repository.EmployeesRepository
import domain.employee.repository.MybatisEmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class EmployeeService(
    private val employeeRepository: MybatisEmployeeRepository
) : Log {

    fun insertEmployee(insertRequest: EmployeeInsertRequest): Employee {
        val newEmployee = Employee(
            birthDate = insertRequest.birthDate,
            firstName = insertRequest.firstName,
            lastName = insertRequest.lastName,
            gender = insertRequest.gender,
            hireDate = insertRequest.hireDate
        )
        employeeRepository.saveEmployee(newEmployee)
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
        check(employeeRepository.updateEmployee(empNo,
            firstName = updateRequest.firstName,
            lastName = updateRequest.lastName,
            birthDate = updateRequest.birthDate,
            gender = updateRequest.gender,
            hireDate = updateRequest.hireDate))
        return findEmployeeByEmpNo(empNo)
    }

    @Transactional(readOnly = true)
    fun findEmployeeByPeriod(startDate: LocalDate, endDate: LocalDate, pageRequest: PageRequest): PageResponse<Employee> {
        val employees: Page<Employee> = employeeRepository.findEmployeeBetweenStartDateAndEndDate(startDate, endDate, pageRequest)
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
        check(employeeRepository.deleteEmployee(empNo)){ "delete employee fail!!" }
        check(employeeRepository.saveDeletedEmployee(
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
    private fun findEmployeeOrThrow(empNo: Int): Employee = employeeRepository.findByEmpNo(empNo)
        ?: throw ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, Employee::class)

    @Transactional(readOnly = true)
    fun findEmployeeByName(name: String): Employee = employeeRepository.findByFirstName(name)
        ?: throw ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, Employee::class)
}