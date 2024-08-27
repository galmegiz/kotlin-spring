package demo.auth.entity

data class RoleHierarchy(
    var id: Int? = null,
    var roleName: String,
    var parentRoleId: Int? = null
) {
    val chileRoles: MutableSet<RoleHierarchy> = mutableSetOf()
}