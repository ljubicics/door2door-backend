package rs.edu.raf.door2doorbackend.user.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.NonNull

@Entity
@Table(name = "users")
data class User(
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
    val address: String? = null
)
