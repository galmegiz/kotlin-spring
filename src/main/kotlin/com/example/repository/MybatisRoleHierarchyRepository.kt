package com.example.repository

import com.example.domain.RoleHierarchy
import com.example.repository.mapper.RoleHierarchyMapper
import org.springframework.stereotype.Repository

@Repository
class MybatisRoleHierarchyRepository(
    private val roleHierarchyMapper: RoleHierarchyMapper
): RoleHierarchyRepository{
    override fun save(roleHierarchy: RoleHierarchy) {
        require(roleHierarchyMapper.save(roleHierarchy) > 0) { "save role fail" }
    }

    override fun findALl(): List<RoleHierarchy> {
        val roleMap: Map<Int, RoleHierarchy> = roleHierarchyMapper.findAll().associateBy { it.id!! }
        roleMap.values.forEach {
            if(it.parentRoleId == null) return@forEach
            roleMap[it.parentRoleId]!!.chileRoles.add(it)
        }

        roleMap.values.forEach {
            for (chileRole in it.chileRoles) {
                it.chileRoles.addAll(chileRole.chileRoles)
            }
        }
        return roleMap.values.toList()
    }
}