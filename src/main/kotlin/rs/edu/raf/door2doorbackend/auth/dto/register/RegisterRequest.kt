package rs.edu.raf.door2doorbackend.auth.dto.register

data class RegisterRequest(
    val username: String = "",
    val password: String = "",
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val mobileNumber: String? = null,
    val address: String? = null,
)
