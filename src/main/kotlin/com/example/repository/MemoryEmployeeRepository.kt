package com.example.repository

import com.example.common.Page
import com.example.common.PageRequest
import com.example.constant.Gender
import com.example.domain.Employee
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class MemoryEmployeeRepository : EmployeesRepository {
    private val employeeMap: MutableMap<Int, Employee> = mutableMapOf()

    @PostConstruct
    fun init(){
        for (i in 0..10) {
            val testEmployee = Employee(
                empNo = i,
                birthDate = LocalDate.now().minusYears(i * 10L),
                firstName = "JONGWOO $i",
                lastName = "SUN",
                Gender.entries[i % 2],
                LocalDate.now().minusYears(i.toLong())
            )

            employeeMap[i] = testEmployee
        }

    }

    override fun findByEmpNo(empNo: Int): Employee? {
        return employeeMap[empNo]
    }

    override fun updateEmployee(
        empNo: Int,
        birthDate: LocalDate?,
        firstName: String?,
        lastName: String?,
        gender: Gender?,
        hireDate: LocalDate?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun findEmployeeBetweenStartDateAndEndDate(
        startDate: LocalDate,
        endDate: LocalDate,
        pageRequest: PageRequest
    ): Page<Employee> {
        TODO("Not yet implemented")
    }

    override fun deleteEmployee(empNo: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun saveDeletedEmployee(
        empNo: Int,
        birthDate: LocalDate,
        firstName: String,
        lastName: String,
        gender: Gender,
        hireDate: LocalDate,
        deletedDate: LocalDate
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun saveEmployee(employee: Employee): Int {
        TODO("Not yet implemented")
    }

    override fun findByFirstName(firstName: String): Employee? {
        TODO("Not yet implemented")
    }
}