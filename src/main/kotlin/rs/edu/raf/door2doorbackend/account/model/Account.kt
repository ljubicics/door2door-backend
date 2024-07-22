package rs.edu.raf.door2doorbackend.account.model

import jakarta.persistence.*
import rs.edu.raf.door2doorbackend.role.model.Role
import rs.edu.raf.door2doorbackend.user.model.User

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String? = null,
    val password: String? = null,
    @ManyToOne
    var role: Role? = null,
    @OneToOne
    var user: User? = null
)
