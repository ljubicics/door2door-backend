package rs.edu.raf.door2doorbackend.account.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import rs.edu.raf.door2doorbackend.user.model.User

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String? = null,
    val password: String? = null,
    var role: String? = null,
    @OneToOne
    var user: User? = null
)
