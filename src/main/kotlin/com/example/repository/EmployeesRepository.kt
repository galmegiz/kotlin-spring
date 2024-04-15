package com.example.repository

import com.example.domain.Employee

interface EmployeesRepository {
    fun getEmployee(empNo: Int): Employee?
}