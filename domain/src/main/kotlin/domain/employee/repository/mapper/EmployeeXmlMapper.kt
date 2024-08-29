package domain.employee.repository.mapper

import domain.employee.entity.Employee
import org.apache.ibatis.annotations.Mapper

@Mapper
interface EmployeeXmlMapper {
    fun findByFirstName(firstName: String): Employee?
}