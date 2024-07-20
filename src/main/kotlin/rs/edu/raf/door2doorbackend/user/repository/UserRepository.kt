package rs.edu.raf.door2doorbackend.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rs.edu.raf.door2doorbackend.user.model.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
}