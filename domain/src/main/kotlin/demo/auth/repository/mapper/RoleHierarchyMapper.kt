package demo.auth.repository.mapper

import demo.auth.entity.RoleHierarchy
import demo.auth.repository.ROLE_HIERARCHY_TABLE
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

@Mapper
interface RoleHierarchyMapper {
    @Insert("INSERT INTO $ROLE_HIERARCHY_TABLE (role_name, parent_role_id) VALUES (#{roleName}, #{parentRoleId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun save(
        roleHierarchy: RoleHierarchy
    ): Int

    @Select("SELECT * FROM $ROLE_HIERARCHY_TABLE")
    fun findAll(): List<RoleHierarchy>


}