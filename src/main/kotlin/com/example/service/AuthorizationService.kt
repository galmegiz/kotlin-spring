package com.example.service

import com.example.domain.RoleHierarchy
import com.example.domain.User
import com.example.repository.RoleHierarchyRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.concurrent.locks.ReentrantReadWriteLock

@Service
class AuthorizationService(
    private val roleHireArchyRepository: RoleHierarchyRepository
) {
    private val lock = ReentrantReadWriteLock()
    private val readLock = lock.readLock()
    private val writeLock = lock.writeLock()
    private val roleContainer: MutableMap<String, RoleHierarchy> = HashMap()

    @PostConstruct
    fun init() {
        val allHierarchy: List<RoleHierarchy> = roleHireArchyRepository.findALl()
        allHierarchy.associateByTo(roleContainer) { it.roleName }
    }

    fun authorize(user: User, requiredRole: String): Boolean {
        readLock.lock()
        try {
            val targetRole = roleContainer[requiredRole] ?: throw IllegalArgumentException("$requiredRole does not exist in roleHierarchy")
            val userRole = roleContainer[user.roleName] ?: return false
            return userRole.chileRoles.contains(targetRole)
        } finally {
            readLock.unlock()
        }
    }

    fun addRole(newRoleName: String, parentRoleName: String, childRoleName: String?) {
        writeLock.lock()
        try{
            var parentRole: RoleHierarchy? = roleContainer[parentRoleName] ?: throw IllegalArgumentException("$parentRoleName does not exist in roleHierarchy")
            val newRole = RoleHierarchy(roleName = newRoleName, parentRoleId = parentRole!!.parentRoleId)
            roleHireArchyRepository.save(newRole)

            // newRole 저장
            if (childRoleName != null && roleContainer.containsKey(childRoleName)) {
                val childRole = roleContainer[childRoleName]!!
                newRole.chileRoles.add(childRole)
                newRole.chileRoles.addAll(childRole.chileRoles)
            }

            do {
                parentRole!!.chileRoles.add(newRole)
                parentRole = roleContainer[parentRole.roleName]
            }while(parentRole != null)

        } finally {
            writeLock.unlock()
        }
    }

}