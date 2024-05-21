package com.example.repository

import com.example.common.Page
import com.example.common.PageRequest
import com.example.constant.Gender
import com.example.domain.Employee
import com.example.repository.mapper.DeletedEmployeeMapper
import com.example.repository.mapper.EmployeeMapper
import com.example.repository.mapper.EmployeeXmlMapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class MybatisEmployeeRepository(
    private val employeeMapper: EmployeeMapper,
    private val employeeXmlMapper: EmployeeXmlMapper,
    private val deletedEmployeeMapper: DeletedEmployeeMapper
) : EmployeesRepository {

    override fun findByEmpNo(@Param("empNo") empNo: Int): Employee? {
        return employeeMapper.findByEmpNo(empNo)
    }

    override fun findByFirstName(firstName: String): Employee? {
        return employeeXmlMapper.findByFirstName(firstName)
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
        = deletedEmployeeMapper.saveDeletedEmployee(
            empNo = empNo,
            birthDate = birthDate,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            hireDate = hireDate,
            deletedDate = deletedDate
        )

    override fun saveEmployee(
        employee: Employee
    ): Int {
        return employeeMapper.saveEmployee(employee)
    }
}