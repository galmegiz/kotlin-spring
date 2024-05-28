package com.example.domain

data class RoleHierarchy(
    var id: Int? = null,
    var roleName: String,
    var parentRoleId: Int? = null
) {
    val chileRoles: MutableSet<RoleHierarchy> = mutableSetOf()
}