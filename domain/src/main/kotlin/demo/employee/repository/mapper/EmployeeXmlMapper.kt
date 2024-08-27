package demo.employee.repository.mapper

import demo.employee.entity.Employee
import org.apache.ibatis.annotations.Mapper

@Mapper
interface EmployeeXmlMapper {
    fun findByFirstName(firstName: String): Employee?
}