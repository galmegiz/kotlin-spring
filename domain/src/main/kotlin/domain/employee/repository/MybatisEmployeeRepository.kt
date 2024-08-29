package domain.employee.repository

import domain.common.Page
import domain.common.PageRequest
import domain.common.constant.Gender
import domain.employee.entity.Employee
import domain.employee.repository.mapper.DeletedEmployeeMapper
import domain.employee.repository.mapper.EmployeeMapper
import domain.employee.repository.mapper.EmployeeXmlMapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class MybatisEmployeeRepository(
    private val employeeMapper: EmployeeMapper,
    private val employeeXmlMapper: EmployeeXmlMapper,
    private val deletedEmployeeMapper: DeletedEmployeeMapper
) : EmployeesRepository {

    override fun findByEmpNo(@Param("empNo") empNo: Int): Employee? {
        return employeeMapper.findByEmpNo(empNo)
    }

    override fun findByFirstName(firstName: String): Employee? {
        return employeeXmlMapper.findByFirstName(firstName)
    }

    override fun updateEmployee(
        empNo: Int,
        birthDate: LocalDate?,
        firstName: String?,
        lastName: String?,
        gender: Gender?,
        hireDate: LocalDate?
    ): Boolean {
        return employeeMapper.updateEmployee(empNo, birthDate, firstName, lastName, gender, hireDate)
    }

    override fun findEmployeeBetweenStartDateAndEndDate(
        startDate: LocalDate,
        endDate: LocalDate,
        pageRequest: PageRequest
    ): Page<Employee> {
        val count = employeeMapper.getEmployeeCount()
        val employees: List<Employee> = employeeMapper.findBetweenStartDateAndEndDateOrderByASC(startDate, endDate, pageRequest)
        return Page(
            content = employees,
            pageRequest = pageRequest,
            totalElements = count
        )
    }

    override fun deleteEmployee(empNo: Int): Boolean
        = employeeMapper.deleteEmployeeByEmpNo(empNo)

    override fun saveDeletedEmployee(
        empNo: Int,
        birthDate: LocalDate,
        firstName: String,
        lastName: String,
        gender: Gender,
        hireDate: LocalDate,
        deletedDate: LocalDate
    ): Boolean
        = deletedEmployeeMapper.saveDeletedEmployee(
            empNo = empNo,
            birthDate = birthDate,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            hireDate = hireDate,
            deletedDate = deletedDate
        )

    override fun saveEmployee(
        employee: Employee
    ): Int {
        return employeeMapper.saveEmployee(employee)
    }
}