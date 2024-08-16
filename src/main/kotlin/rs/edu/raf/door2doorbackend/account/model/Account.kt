package rs.edu.raf.door2doorbackend.account.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import rs.edu.raf.door2doorbackend.role.model.Role
import rs.edu.raf.door2doorbackend.user.model.User

@Entity
open class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var username: String? = null,
    @JsonIgnore
    var password: String? = null,
    @ManyToOne
    var role: Role? = null,
    @OneToOne
    var user: User? = null
)
