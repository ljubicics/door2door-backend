package rs.edu.raf.door2doorbackend.user.service

import org.springframework.stereotype.Service
import rs.edu.raf.door2doorbackend.user.model.User
import rs.edu.raf.door2doorbackend.user.repository.UserRepository

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun saveUser(user: User) {
        userRepository.save(user)
    }
}