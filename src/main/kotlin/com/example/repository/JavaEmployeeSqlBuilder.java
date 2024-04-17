package com.example.repository;

import com.example.constant.Gender;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class JavaEmployeeSqlBuilder {
    public static String buildUpdateEmployee(int empNo, LocalDate birthDate, String firstName, String lastName, Gender gender, LocalDate hireDate) {
        return new SQL() {{
            UPDATE("employee");
            if (birthDate != null) SET("birthDate = #{birthDate}");
            if (firstName != null) SET("firstName = #{firstName}");
            if (lastName != null) SET("lastName = #{lastName}");
            if (gender != null) SET("gender = #{gender}");
            if (hireDate != null) SET("hireDate = #{hireDate}");
            WHERE("empNo = #{empNo}");
        }}.toString();
    }
}
