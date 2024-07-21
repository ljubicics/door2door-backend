package rs.edu.raf.door2doorbackend.account.dto

import rs.edu.raf.door2doorbackend.auth.util.AuthenticationDetails

data class AccountDto(
    val id: Long,
    val username: String,
    val password: String,
    val role: String
) : AuthenticationDetails {

    override fun fetchId(): Long {
        return id
    }

    override fun fetchUsername(): String {
        return username
    }

    override fun fetchRole(): String {
        return role
    }
}