package domain.auth.repository

import domain.auth.entity.RoleHierarchy

interface RoleHierarchyRepository {
    fun save(roleHierarchy: RoleHierarchy)
    fun findALl(): List<RoleHierarchy>
}