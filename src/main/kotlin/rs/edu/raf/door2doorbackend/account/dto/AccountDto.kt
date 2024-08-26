package rs.edu.raf.door2doorbackend.account.dto

import rs.edu.raf.door2doorbackend.auth.util.AuthenticationDetails
import rs.edu.raf.door2doorbackend.role.model.Role

data class AccountDto(
    val id: Long,
    val username: String,
    val password: String,
    val role: Role,
    val numberOfDeliveries: Int
) : AuthenticationDetails {

    override fun fetchId(): Long {
        return id
    }

    override fun fetchUsername(): String {
        return username
    }

    override fun fetchRole(): String {
        return role.name.toString()
    }
}