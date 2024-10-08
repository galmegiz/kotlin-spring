package domain.employee.repository.mapper

import domain.common.PageRequest
import domain.common.constant.Gender
import domain.employee.entity.Employee
import domain.employee.repository.EMPLOYEE_TABLE
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.jdbc.SQL
import java.time.LocalDate

@Mapper
interface EmployeeMapper {

    @InsertProvider(type = EmployeeSqlBuilder::class, method = "buildInsertEmployee")
    @Options(useGeneratedKeys = true, keyProperty = "empNo", keyColumn = "emp_no")
    fun saveEmployee(
        employee: Employee
    ): Int

    @Select("SELECT count(*) FROM $EMPLOYEE_TABLE")
    fun getEmployeeCount(): Int

    @Select("SELECT * FROM $EMPLOYEE_TABLE WHERE emp_no = #{empNo}")
    fun findByEmpNo(empNo: Int): Employee?

    @Select("SELECT * FROM $EMPLOYEE_TABLE " +
            "WHERE hire_date BETWEEN #{startDate} AND #{endDate} " +
            "ORDER BY hire_date ASC " +
            "LIMIT #{pageRequest.pageNumber}, #{pageRequest.pageSize}")
    fun findBetweenStartDateAndEndDateOrderByASC(startDate: LocalDate, endDate: LocalDate, pageRequest: PageRequest): List<Employee>

    @Delete("DELETE FROM $EMPLOYEE_TABLE " +
            "WHERE emp_no = #{empNo}")
    fun deleteEmployeeByEmpNo(empNo: Int): Boolean

    @UpdateProvider(type = EmployeeSqlBuilder::class, method = "buildUpdateEmployee")
    fun updateEmployee(
        empNo: Int,
        birthDate: LocalDate?,
        firstName: String?,
        lastName: String?,
        gender: Gender?,
        hireDate: LocalDate?
    ): Boolean

    class EmployeeSqlBuilder {
        fun buildInsertEmployee(
          employee: Employee
        ): String {
            return object : SQL(){
                init {
                    INSERT_INTO(EMPLOYEE_TABLE)
                    VALUES("birth_date", "#{birthDate}")
                    VALUES("first_name", "#{firstName}")
                    VALUES("last_name", "#{lastName}")
                    VALUES("gender", "#{gender}")
                    VALUES("hire_date", "#{hireDate}")
                }
            }.toString()
        }

        fun buildUpdateEmployee(
            empNo: Int,
            birthDate: LocalDate?,
            firstName: String?,
            lastName: String?,
            gender: Gender?,
            hireDate: LocalDate?
        ): String {
            return object : SQL() {
                init {
                    UPDATE(EMPLOYEE_TABLE)
                    if (birthDate != null) SET("birth_date = #{birthDate}")
                    if (firstName != null) SET("first_name = #{firstName}")
                    if (lastName != null) SET("last_name = #{lastName}")
                    if (gender != null) SET("gender = #{gender}")
                    if (hireDate != null) SET("hire_date = #{hireDate}")
                    WHERE("emp_no = #{empNo}")
                }
            }.toString()
        }
    }

}