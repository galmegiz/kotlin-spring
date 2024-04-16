package com.example.repository

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
                lastname = "SUN",
                Gender.entries[i % 2],
                LocalDate.now().minusYears(i.toLong())
            )

            employeeMap[i] = testEmployee
        }

    }

    override fun findByEmpNo(empNo: Int): Employee? {
        return employeeMap[empNo]
    }
}