package rs.edu.raf.door2doorbackend.user.service

import org.springframework.stereotype.Service
import rs.edu.raf.door2doorbackend.account.repository.AccountRepository
import rs.edu.raf.door2doorbackend.user.model.User
import rs.edu.raf.door2doorbackend.user.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository
) {

    fun saveUser(user: User) {
        userRepository.save(user)
    }

    fun getUserInfo(username: String): User {
        val account = accountRepository.findAccountByUsername(username)
        return account?.user ?: throw Exception("User not found")
    }
}