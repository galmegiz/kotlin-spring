package com.example.repository

import com.example.domain.Employee

interface EmployeesRepository {
    fun findByEmpNo(empNo: Int): Employee?
}