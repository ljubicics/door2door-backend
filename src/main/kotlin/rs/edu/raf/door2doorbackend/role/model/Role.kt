package rs.edu.raf.door2doorbackend.role.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import rs.edu.raf.door2doorbackend.role.model.enums.RoleName
import java.io.Serializable

@Entity
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Enumerated(EnumType.STRING)
    val name: RoleName? = null
) : Serializable, GrantedAuthority {
    override fun getAuthority(): String {
        return name.toString()
    }
}
