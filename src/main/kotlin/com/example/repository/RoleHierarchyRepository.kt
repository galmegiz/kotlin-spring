package com.example.repository

import com.example.domain.RoleHierarchy

interface RoleHierarchyRepository {
    fun save(roleHierarchy: RoleHierarchy)
    fun findALl(): List<RoleHierarchy>
}