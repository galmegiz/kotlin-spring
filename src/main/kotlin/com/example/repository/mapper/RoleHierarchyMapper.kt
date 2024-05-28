package com.example.repository.mapper

import com.example.domain.RoleHierarchy
import com.example.repository.table.ROLE_HIERARCHY_TABLE
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import kotlin.system.exitProcess

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