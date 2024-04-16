package com.example.repository

import com.example.domain.Employee
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface EmployeeMapper {
    @Select("SELECT * FROM employees WHERE emp_no = #{empNo}")
    fun findByEmpNo(empNo: Int): Employee?
}