package rs.edu.raf.door2doorbackend.account.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import rs.edu.raf.door2doorbackend.role.model.Role
import rs.edu.raf.door2doorbackend.user.model.User

@Entity
open class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0,
    open var username: String? = null,
    @JsonIgnore
    open var password: String? = null,
    @ManyToOne
    open var role: Role? = null,
    @OneToOne
    open var user: User? = null,
    open var numberOfDeliveries: Int = 0,
)
