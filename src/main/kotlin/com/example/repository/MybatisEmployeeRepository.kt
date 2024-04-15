package com.example.repository

import com.example.domain.Employee
import org.springframework.stereotype.Repository

@Repository
class MybatisEmployeeRepository : EmployeesRepository {
    override fun getEmployee(empNo: Int): Employee? {
        TODO("Not yet implemented")
    }
}