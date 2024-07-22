package rs.edu.raf.door2doorbackend.auth.util

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import rs.edu.raf.door2doorbackend.account.service.AccountService

@Component
class CustomDetailsService(
    private val accountService: AccountService
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        try {
            val account = accountService.findByUsername(username)
            val authorities = listOf(SimpleGrantedAuthority(account.role.name.toString()))
            return User(account.username, account.password, authorities)
        } catch (e: Exception) {
            throw Exception("User not found")
        }
    }
}