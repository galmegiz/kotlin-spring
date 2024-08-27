package demo.auth.repository

import demo.auth.entity.RoleHierarchy

interface RoleHierarchyRepository {
    fun save(roleHierarchy: RoleHierarchy)
    fun findALl(): List<RoleHierarchy>
}