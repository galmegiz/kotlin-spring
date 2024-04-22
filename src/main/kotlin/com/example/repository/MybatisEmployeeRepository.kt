package com.example.repository

import com.example.common.ErrorCode
import com.example.common.Page
import com.example.common.PageRequest
import com.example.constant.Gender
import com.example.domain.Employee
import com.example.exception.ResourceNotFoundException
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.time.LocalDate

@Repository
class MybatisEmployeeRepository(
    private val employeeMapper: EmployeeMapper,
    private val deletedEmployeeMapper: DeletedEmployeeMapper
) : EmployeesRepository {

    override fun findByEmpNo(@Param("empNo") empNo: Int): Employee? {
        return employeeMapper.findByEmpNo(empNo)
    }

    override fun updateEmployee(
        empNo: Int,
        birthDate: LocalDate?,
        firstName: String?,
        lastName: String?,
        gender: Gender?,
        hireDate: LocalDate?
    ): Boolean {
        return employeeMapper.updateEmployee(empNo, birthDate, firstName, lastName, gender, hireDate)
    }

    override fun findEmployeeBetweenStartDateAndEndDate(
        startDate: LocalDate,
        endDate: LocalDate,
        pageRequest: PageRequest
    ): Page<Employee> {
        val count = employeeMapper.getEmployeeCount()
        val employees: List<Employee> = employeeMapper.findBetweenStartDateAndEndDateOrderByASC(startDate, endDate, pageRequest)
        return Page(
            content = employees,
            pageRequest = pageRequest,
            totalElements = count
        )
    }

    override fun deleteEmployee(empNo: Int): Boolean
        = employeeMapper.deleteEmployeeByEmpNo(empNo)

    override fun saveDeletedEmployee(
        empNo: Int,
        birthDate: LocalDate,
        firstName: String,
        lastName: String,
        gender: Gender,
        hireDate: LocalDate,
        deletedDate: LocalDate
    ): Boolean
        = throw IllegalArgumentException()
        /*= deletedEmployeeMapper.saveDeletedEmployee(
            empNo = empNo,
            birthDate = birthDate,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            hireDate = hireDate,
            deletedDate = deletedDate
        )*/
}