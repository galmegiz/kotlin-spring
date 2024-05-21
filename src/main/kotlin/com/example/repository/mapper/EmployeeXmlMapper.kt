package com.example.repository.mapper

import com.example.domain.Employee
import org.apache.ibatis.annotations.Mapper

@Mapper
interface EmployeeXmlMapper {
    fun findByFirstName(firstName: String): Employee?
}