package com.example.repository

import com.example.domain.Employee
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
class MybatisEmployeeRepository(
    private val employeeMapper: EmployeeMapper
) : EmployeesRepository {

    override fun findByEmpNo(@Param("empNo") empNo: Int): Employee? {
        return employeeMapper.findByEmpNo(empNo)
    }
}