package rs.edu.raf.door2doorbackend.role.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rs.edu.raf.door2doorbackend.role.model.Role
import rs.edu.raf.door2doorbackend.role.model.enums.RoleName

@Repository
interface RoleRepository : JpaRepository<Role, Long> {

    fun findRoleByName(name: RoleName): Role?
}