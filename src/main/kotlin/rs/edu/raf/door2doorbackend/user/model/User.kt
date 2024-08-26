package rs.edu.raf.door2doorbackend.user.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.NonNull
import org.springframework.data.redis.core.RedisHash

@Entity
@Table(name = "users")
@RedisHash("User")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NonNull
    val name: String? = null,
    @NonNull
    val surname: String? = null,
    @JsonIgnore
    val email: String? = null,
    val mobileNumber: String? = null,
    val address: String? = null,
    val timeCreated: Long? = null,
)
